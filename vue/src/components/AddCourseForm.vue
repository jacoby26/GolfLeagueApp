<template>
    <div>
        <!-- <div id="intro">
          <h1>Add a Course</h1>
        </div> -->
        <form id="add-course" v-on:submit.prevent="submitForm()">
            <div>
                <label for="course_name">Course Name: </label><br>
                <input id="course_name" type="text" v-model="course.courseName" autocomplete="off" />
            </div>
            <div>
                <label for="course_name">Street Address: </label><br>
                <input id="course_name" type="text" v-model="course.address" autocomplete="off" />
            </div>
            <div>
                <label for="course_name">City: </label><br>
                <input id="course_name" type="text" v-model="course.city" autocomplete="off" />
            </div>
            <div>
                <label for="course_name">State: </label><br>
                <input id="course_name" type="text" v-model="course.state" autocomplete="off" />
            </div>
            <div>
                <label for="course_name">Zip Code: </label><br>
                <input id="course_name" type="text" v-model="course.zip" autocomplete="off" />
            </div>
            <!-- <div>
                <label for="course_name">Latitude: </label>
                <input id="course_name" type="text" v-model="course.latitude" autocomplete="off" />
            </div>
            <div>
                <label for="course_name">Longitude: </label>
                <input id="course_name" type="text" v-model="course.longitude" autocomplete="off" />
            </div> -->
            <button class="btn course-submit"><span>Submit</span></button><br>
            <button class="btn course-cancel" v-on:click.prevent="cancelForm" type="cancel"><span>Cancel</span></button>
        </form>

    <!-- course_name, address, city, course_state, zip_code, latitude, longitude -->
    </div>
</template>

<script>
import golfCourseService from '../services/CourseService.js'

export default {
    name: "add-course-form",
    data(){
      return {
        course: {
            courseName: "",
            address: "",
            city: "",
            state: "",
            zip: "",
            latitude: 0,
            longitude: 0
            },
      }
    },
    methods: {
        submitForm() {
            
            this.getLatLngByAddress(this.makeAddress()).then(results => {
                this.course.latitude = results[0]
                console.log(this.course.latitude)
                this.course.longitude = results[1]
                let newCourse = {
                    courseName: this.course.courseName,
                    address: this.course.address,
                    city: this.course.city,
                    state: this.course.state,
                    zip: this.course.zip,
                    latitude: this.course.latitude,
                    longitude: this.course.longitude
                };

                golfCourseService
                .addCourse(newCourse)
                .then(response => {
                    if (response.status === 200) {
                        window.alert("Course added successfully!");
                    } else {
                        window.alert("Problem adding course")
                    }
                })
            })
            
        },
        getLatLngByAddress(address) {
            var geocoder = new window.google.maps.Geocoder(); 
            var latitude = 0
            var longitude = 0
            // console.log(address)
            return new Promise(function(resolve, reject) {
            geocoder.geocode({ 'address': address }, function (results, status) {
                if (status == window.google.maps.GeocoderStatus.OK) {
                    latitude = results[0].geometry.location.lat();
                    longitude = results[0].geometry.location.lng();
                    resolve ([parseFloat(latitude), parseFloat(longitude)]);
                } else {
                    reject(new Error('Couldnt\'t find the location ' + address));
                    console.log('nope')
                }
            })
            
            })
        },
        makeAddress() {
            return this.course.address + ", " + this.course.city + ", " + this.course.state + " " + this.course.zip
        }
    }
}
</script>

<style>
.course-cancel {
  display: inline-block;
  border-radius: 5px;
  border: none;
  color: #005229;
  text-align: center;
  font-size: 15px;
  padding: 10px;
  width: 50.5%;
  margin-top: 5px;
  margin-left: 5px;
  margin-bottom: 5px;
  transition-duration: 0.4s;
}

.course-cancel:hover {
    background-color: #ffb81f;
    cursor: pointer;
    color: white;
}

.course-submit {
  display: inline-block;
  border-radius: 5px;
  background-color: #005229;
  border: none;
  color: #FFFFFF;
  text-align: center;
  font-size: 15px;
  padding: 10px;
  width: 50.5%;
  margin-top: 5px;
  margin-left: 5px;
  margin-bottom: 5px;
  transition: all 0.5s;
}

.course-submit span {
  display: inline-block;
  position: relative;
  transition: 0.5s;
}

.course-submit span:after {
  content: '\00bb';
  position: absolute;
  opacity: 0;
  top: 0;
  right: -20px;
  transition: 0.5s;
}

.course-submit:hover span {
  padding-right: 25px;
  cursor: pointer;
}

.course-submit:hover span:after {
  opacity: 1;
  right: 0;
}

#course_name {
    margin: 5px;
    border-radius: 3px;
    width: 50%;
    height: 20px;
}



</style>