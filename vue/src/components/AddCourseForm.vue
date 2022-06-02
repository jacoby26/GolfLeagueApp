<template>
    <div>
        <form v-on:submit.prevent="submitForm()">
            <div>
                <label for="course_name">Course Name: </label>
                <input id="course_name" type="text" v-model="course.courseName" autocomplete="off" />
            </div>
            <div>
                <label for="course_name">Street Address: </label>
                <input id="course_name" type="text" v-model="course.address" autocomplete="off" />
            </div>
            <div>
                <label for="course_name">City: </label>
                <input id="course_name" type="text" v-model="course.city" autocomplete="off" />
            </div>
            <div>
                <label for="course_name">State: </label>
                <input id="course_name" type="text" v-model="course.state" autocomplete="off" />
            </div>
            <div>
                <label for="course_name">Zip Code: </label>
                <input id="course_name" type="text" v-model="course.zip" autocomplete="off" />
            </div>
            <div>
                <label for="course_name">Latitude: </label>
                <input id="course_name" type="text" v-model="course.latitude" autocomplete="off" />
            </div>
            <div>
                <label for="course_name">Longitude: </label>
                <input id="course_name" type="text" v-model="course.longitude" autocomplete="off" />
            </div>
            <button class="btn btn-submit">Submit</button>
            <button class="btn btn-cancel" v-on:click.prevent="cancelForm" type="cancel">Cancel</button>
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
            zip: 0,
            latitude: 0,
            longitude: 0
            },
      }
    },
    methods: {
        submitForm() {
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
        }
    }
}
</script>
<style>
</style>