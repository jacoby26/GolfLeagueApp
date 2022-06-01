<template>
  <div>
    <div id="grid-container">
      <div id="intro">
          <h1>Find a Course</h1>
      </div>

      <form id="searchForm" >
          <input type="text" placeholder="Enter your zip code" id="text">
          <button type="submit" v-on:click.prevent="calculateCenter()">Search</button>   
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
    };
  },
  created()
    {
        golfCourseService.getAllCourses()
            .then((response) =>
            {
                const locations = response.data
                console.table(response.data)
                this.$store.commit('LOAD_COURSES', locations)
            })
    },
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
        this.getLatLngByZipcode(this.getUserInput()).then(results => {
           console.log(results)
           this.mapCenter = {
           lat: parseFloat(results[0]),
           lng: results[1]
       }
       this.initMap()
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

    getLocations() {
        golfCourseService.getAllCourses().then(response => {
            this.locations = response.data
            
            console.table(response.data)
        })

        console.log(this.locations)
    },
    makeMarkerObj(lat, lng, name) {
      const latLng = {
        lat: lat,
        lng: lng
      }
      const markerObj = { coord: latLng, name: name };
      return markerObj;
    },

    dropPins() {
      
      this.$store.state.locations.forEach((x) => {
       let makeMarker =  this.makeMarkerObj(x.latitude, x.longitude, x.name)
       console.log(makeMarker);
        this.dropPin(makeMarker)});

      
    },




    dropPin(markerObj) {
      new window.google.maps.Marker({
        position: markerObj.coord,
        map: this.map,
        label: {
          text: markerObj.name,
          color: "blue",
        },
      });
    },
  },
  mounted() {
    this.initMap();
    this.dropPins();
    // this.getLocations()
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