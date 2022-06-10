<template>
    <div id="add-course-body">
        <form v-on:submit.prevent="submitForm()" class="add-course-form">
            <!-- <div class="add-course-div"> -->
                <label class="course_name_label" for="course_name">Course Name</label>
                <input class="course_name_input" id="course_name" type="text" v-model="course.courseName" placeholder="Course Name" autocomplete="off" />
            <!-- </div>
            <div class="add-course-div"> -->
                <label class="street_address_label" for="course_name">Street Address</label>
                <input class="street_address_input" id="course_name" type="text" v-model="course.address" placeholder="Street Address" autocomplete="off" />
            <!-- </div>
            <div class="add-course-div"> -->
                <label class="city_label" for="course_name">City</label>
                <input class="city_input" id="course_name" type="text" v-model="course.city" placeholder="City" autocomplete="off" />
            <!-- </div>
            <div class="add-course-div"> -->
                <label class="state_label" for="course_name">State</label>
                <input class="state_input" id="course_name" type="text" v-model="course.state" placeholder="State" autocomplete="off" />
            <!-- </div>
            <div class="add-course-div"> -->
                <label class="zip_code_label" for="course_name" >Zip Code</label>
                <input class="zip_code_input" id="course_name" type="text" v-model="course.zip" placeholder="Zip Code" autocomplete="off" />
            <!-- </div> -->
            <!-- <div>
                <label for="course_name">Latitude: </label>
                <input id="course_name" type="text" v-model="course.latitude" autocomplete="off" />
            </div>
            <div>
                <label for="course_name">Longitude: </label>
                <input id="course_name" type="text" v-model="course.longitude" autocomplete="off" />
            </div> -->
            <span>
                <button class="btn-btn-submit"><span>Add Course</span></button>
                <button class="btn-btn-cancel" v-on:click.prevent="cancelForm" type="cancel"><span>Cancel</span></button>
            </span>
            
                
          
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
.add-course-form, 
.add-course-form:not(.btn-btn-submit), 
.add-course-form:not(.btn-btn-cancel) {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;

    
}

.course_name_label,
.street_address_label, 
.city_label,
.state_label,
.zip_code_label {
    display: flex;
    justify-content: center;
    font-weight: bold;
    font-size: 27px;
    
} 
.course_name_input, 
.street_address_input,
.city_input,
.state_input,
.zip_code_input {
    display: flex;
    justify-content: center;
    width: 35%;
    margin: 1%;
    padding: 6px;
    border-radius: 5px;
}
/* #buttons {
    display: flex;
    flex-direction: row;
    justify-content: center;
} */
.btn-btn-submit {
  display: flex;
  flex-direction: row;
  justify-content: center;
  border-radius: 5px;
  background-color: #005229;
  border: none;
  color: #FFFFFF;
  text-align: center;
  font-size: 15px;
  padding: 10px;
  width: 230px;
  margin-top: 20px;
  transition: all 0.5s;
  text-shadow: 1px 1px black;
}

.btn-btn-submit  span {
  display: inline-block;
  position: relative;
  transition: 0.5s;
}

.btn-btn-submit span:after {
  content: '\00bb';
  position: absolute;
  opacity: 0;
  top: 0;
  right: -20px;
  transition: 0.5s;
}

.btn-btn-submit:hover span {
  padding-right: 25px;
  cursor: pointer;
}

.btn-btn-submit:hover span:after {
  opacity: 1;
  right: 0;
}

.btn-btn-cancel {
  display: flex;
  flex-direction: row;
  justify-content: center;
  border-radius: 5px;
  background-color: whitesmoke;
  border-style: solid;
  border-width: thin;
  border-color: #ffb81f;
  color: #ffb81f;
  text-align: center;
  font-size: 15px;
  font-weight: bold;
  padding: 10px;
  width: 230px;
  margin-top: 20px;
  transition: all 0.5s;
  cursor: pointer;
}

.btn-btn-cancel:hover {
background-color: #ffb81f;
color: white;
}
</style>