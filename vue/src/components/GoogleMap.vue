<template>
  <div>
    <div id="grid-container">
      <div id="intro">
          <h1>Find a Course</h1>
      </div>
      <form id="searchForm" >
          <input type="text" placeholder="Enter your zip code" id="text">
          <button type="submit" id="search" :disabled="isLoading" v-on:click.prevent="calculateCenter()">Search</button>   
      </form>

      <div id="map"></div>

      
    </div>
  </div>
</template>

<script>
import golfCourseService from '../services/CourseService.js'

export default {
  name: "Map",
  data() {
    return {
      map: null,
      mapCenter: { lat: 43.0389, lng: -87.9065 }, 
      isLoading: true,
      zip: "53201"
    };
  },
  created(){},
  methods: {
    initMap() {
      this.map = new window.google.maps.Map(document.getElementById("map"), {
        center: this.mapCenter,
        zoom: 14,
        maxZoom: 20,
        minZoom: 3,
        streetViewControl: true,
        mapTypeControl: true,
        fullscreenControl: true,
        zoomControl: true,
      });
      let noPOIStyle = [
        {
          featureType: "poi",
          elementType: "labels",
          stylers: [{ visibility: "off" }],
        },
      ];
      this.map.setOptions({ styles: noPOIStyle });
      
    },
    calculateCenter() {
        this.zip = this.getUserInput()
        this.getLatLngByZipcode(this.zip).then(results => {
           this.mapCenter = {
           lat: parseFloat(results[0]),
           lng: results[1]
           
       }
       this.initMap()
       this.dropPins(this.zip)
       })
       
    },
    
    getLatLngByZipcode(zipcode) {
    var geocoder = new window.google.maps.Geocoder();   
    var address = zipcode;
    var latitude = 0
    var longitude = 0
    console.log(address)
    return new Promise(function(resolve, reject) {
    geocoder.geocode({ 'address': address }, function (results, status) {
        if (status == window.google.maps.GeocoderStatus.OK) {
            latitude = results[0].geometry.location.lat();
            longitude = results[0].geometry.location.lng();
            resolve ([parseFloat(latitude), parseFloat(longitude)]);
        } else {
            reject(new Error('Couldnt\'t find the location ' + zipcode));
        }
    })
    
    })
    },
    getUserInput() {
        var zipcode = document.getElementById('text').value
        console.log(zipcode)
        return "" + zipcode
    },
    makeMarkerObj(lat, lng, name) {
      const latLng = {
        lat: lat,
        lng: lng
      }
      const markerObj = { coord: latLng, name: name };
      return markerObj;
    },

    dropPins(zip) {
        let zips = [];
        
        for(let i = 0; i < 100; i++) {
            zips[i] = "" + ((parseInt(zip) - 50) + (i))
        }

        let loc = []

        for(let j = 0; j < 100; j++){
            let results = this.$store.state.locations.filter((x) => x.zip == zips[j])

            results.forEach((x) => {
                loc.push(x)
            })
        }
        loc.forEach((x) => {
            this.dropPin(this.makeMarkerObj(x.latitude, x.longitude, x.courseName))
        });  
      // this.markerObj.description = new window.google.maps.InfoWindow({
      // content:"HTML Content goes here"
      // });
      // window.google.maps.event.addListener(this.markerObj, 'click', function(){
      // this.description.setPosition(this.getPosition());
      // this.description.open(this.map); //map to display on
      // }); 
    },
    dropPin(markerObj) {
      const golfIcon = {
        url: "kisspng-golf-club-sport-scalable-vector-graphics-icon-golf-5aa2658063b298.7544339315205922564084.png",
        scaledSize: new window.google.maps.Size(50, 50),
      }
      const marker = new window.google.maps.Marker({
        position: markerObj.coord,
        map: this.map,
        icon: golfIcon,
        // label: {
        //   text: " ",
        //   color: "black",
        // },
      }); 
      const infowindow = new window.google.maps.InfoWindow({
      content: markerObj.name,
  });
      marker.addListener("click", () => {
      infowindow.open({
      anchor: marker,
      shouldFocus: true,
    });
   });
    },
    getAllCourses() {
        golfCourseService.getAllCourses()
            .then((response) =>
            {
                const locations = response.data
                console.table(response.data)
                this.$store.commit('LOAD_COURSES', locations)
                this.isLoading = false
                this.dropPins(this.zip)
    
            })
    }
  },
  mounted() {
    this.initMap();
    this.getAllCourses();
  },
};
</script>

<style>

#map {
  grid-area: map;
  width: 500px;
  height: 400px;
  padding: 25px;
  margin: 25px; 
} 

</style>