<template>
    <form  v-on:submit.prevent="submitForm()" >
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


export default {
    data(){
        return{
            league:{
                name: '',
                organizer: this.$store.state.user,
                courseName: '',
                courseID: '',
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
                players: this.league.players
            };
            this.setCourseId()
            leagueService
            .addLeague(newLeague)
            .then(response => {
                if (response.status === 201) {
                    window.alert("League added successfully!");
                } else {
                    window.alert("Problem adding League")
                }
            })
        },
        setCourseId() {
            let locs = this.$store.state.location.filter((x) => x.courseName == this.league.courseName)

            this.league.courseID = locs[0].courseID

            // let results = this.$store.state.locations.filter((x) => x.zip == zips[j])
        }
        
    }
}
</script>
