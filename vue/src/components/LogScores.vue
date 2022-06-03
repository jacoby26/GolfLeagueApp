<template>
    <div>
        <div id="intro">
          <h1>Log Match Scores</h1>
        </div>
        <form v-on:submit.prevent="submitForm()">
            <label for="course_name">Round: </label>
            <select>
                <!-- Will make v-for loop once league service is updated -->
                <option value="">Round 0</option>
                <option value="Active">Round 1</option>
                <option value="Disabled">Round 2</option>
            </select>
            <div>
                <label for="course_name">Player: </label>
                <input id="course_name" type="text" v-model="course.courseName" autocomplete="off" />
            </div>
            
            <div>
                <label for="course_name">Score: </label>
                <input id="course_name" type="text" v-model="course.address" autocomplete="off" />
            </div>
            
            <button class="btn btn-submit">Submit</button>
            <button class="btn btn-cancel" v-on:click.prevent="cancelForm" type="cancel">Cancel</button>
        </form>
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
            const newCourse = {
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
                if (response.status === 201) {
                    window.alert("Course added successfully!");
                } else {
                    window.alert("Problem adding course")
                }
            })
        }
    }
}
</script>