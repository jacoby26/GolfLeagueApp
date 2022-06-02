<template>
    <form  v-on:submit.prevent="getAllCourses" >
          <h1>Register League</h1>
        <div>
            <label for="league-name">League name: </label>
            <input
                type="text"
                id="league-name"
                placeholder="The Extraordinary"
                v-model="league.name"
                required
            />
        </div>
        <div>
            <label for="course">Course Name: </label>
            <input type="text" id="league-course" placeholder="Enter course name here" required v-model="league.courseName" />
            <button type="submit">Register League</button>
        </div>
      </form>
</template>

<script>
import leagueService from "../services/LeagueService.js"
import golfCourseService from "../services/CourseService.js"


export default {
    data(){
        return{
            league:{
                name: '',
                organizer: this.$store.state.user,
                courseName: '',
                course: {},
                players:[this.$store.state.user]
            }
        }
    },
    methods: {
        submitForm() {
            const newLeague = {
                name: this.league.name,
                organizer: this.league.user,
                courseName: this.league.courseName,
                course: this.league.course,
                players: this.league.players
            };
            
            leagueService
            .addLeague(newLeague)
            .then(response => {
                if (response.status === 200) {
                    window.alert("League created successfully!");
                } else {
                    window.alert("Problem creating League")
                }
            })
        },
        setCourse(locations) {
            let locs = locations.filter((x) => x.courseName == this.league.courseName)
            console.log(locations)
            console.log(locs)
            console.log(locs[0])
            this.league.course = locs[0]

            // let results = this.$store.state.locations.filter((x) => x.zip == zips[j])
        },
        getAllCourses() {
        golfCourseService.getAllCourses()
            .then((response) =>
            {
                const locations = response.data
                this.setCourse(locations)
                this.submitForm()
    
            })
    }
        
    }
}
</script>
