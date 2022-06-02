<template>
    <form  v-on:submit.prevent="getAllCoursesForForm" >
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
        
                <!-- Option 1 -- drop-down menu -->
        <!-- <div>
            <label for="course">Course Name: </label>
            <select>
                <option value="" v-for="location in locations" 
            :key="location.courseName"
            v-bind:location="location">{{location.courseName}}</option>
            </select>
        </div> -->
        <div>
            <!-- option 2 -- autocomplete search box -->
            <label for="course">Course Name: </label>
            <input v-model="league.courseName" @input="onChange" type="text"/>
                <ul v-show="isOpen" class="autocomplete-results">
                <li class="autocomplete-result" v-for="location in filteredResults" 
            :key="location.courseID" @click="setResult(location)"> {{ location.courseName }} </li>
                </ul>
            <button type="submit">Register League</button>
        </div>

        <!-- original input box -->
        <div>
            <!-- <label for="course">Course Name: </label>
            <input type="text" id="league-course" placeholder="Enter course name here" required v-model="league.courseName" /> -->
            
        </div>
      </form>
</template>

<script>
import leagueService from "../services/LeagueService.js"
import golfCourseService from "../services/CourseService.js"


export default {
    data(){
        return{
            search: '',
            locations: [],
            filteredResults: [],
            isOpen: false,
            league:{
                name: '',
                organizer: this.$store.state.user,
                courseName: "",
                courseID: '',
                course: {},
                players:[this.$store.state.user]
            }
        }
    },
    mounted() {
       this.getAllCoursesForMenu()
       document.addEventListener('click', this.handleClickOutside);
    },
    methods: {
        handleClickOutside(event) {
            if (!this.$el.contains(event.target)) {
                this.isOpen = false;
            }
        },
        setResult(result) {
            this.league.courseName = result.courseName
            this.league.courseID = result.id;
            this.isOpen = false;
        },
        filterResults() {
            this.filteredResults = this.locations.filter(location => location.courseName.toLowerCase().indexOf(this.league.courseName.toLowerCase()) > -1);
        },
        onChange() {
            this.filterResults();
            this.isOpen = true;
        },
        submitForm() {
            const newLeague = {
                name: this.league.name,
                organizer: this.league.user,
                courseID: this.league.courseID,
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
            let locs = locations.filter((x) => x.id == this.league.courseID)
            console.log(locations)
            console.log(locs)
            console.log(locs[0])
            this.league.course = locs[0]

            // let results = this.$store.state.locations.filter((x) => x.zip == zips[j])
        },
        getAllCoursesForMenu() {
            golfCourseService.getAllCourses().then((response) => {
                this.locations = response.data
            })
        },
        getAllCoursesForForm() {
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

<style scoped>
    .autocomplete {
    position: relative;
  }

  .autocomplete-results {
    padding: 0;
    margin: 0;
    border: 1px solid #eeeeee;
    height: 120px;
    min-height: 1em;
    max-height: 6em;    
    overflow: auto;
  }

  .autocomplete-result {
    list-style: none;
    text-align: left;
    padding: 4px 2px;
    cursor: pointer;
  }

  .autocomplete-result:hover {
    background-color: #4AAE9B;
    color: white;
  }
</style>