<template>
<div>
    <form  v-on:submit.prevent="getAllCoursesForForm" >
          <h1>Register League</h1>
        <div>
            <label for="league-name">League name: </label>
            <input
                type="text"
                id="league-name"
                placeholder=""
                v-model="league.name"
                required
            />
        </div>
        <div>
            <label for="course">Course Name: </label>
            <input v-model="league.courseName" @input="onChange" type="text"/>
                <ul v-show="isOpen" class="autocomplete-results">
                <li class="autocomplete-result" v-for="location in filteredResults" 
            :key="location.courseID" @click="setResult(location)"> {{ location.courseName }} </li>
                </ul>
        </div>
       <div>
            <button type="submit" class="register-button"><span>Register League</span></button>
        </div>
       
      </form>
       
</div>

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
    padding: 7px;
    margin: 2px;
    border: 5px solid #005229;
    border-radius: 5px;
    background-color: #ffffff;
    opacity: 50%;
    height: 120px;
    min-height: 1em;
    max-height: 6em;    
    overflow: auto;
  }

  .autocomplete-result {
    list-style: none;
    text-align: left;
    padding: 4px 2px;
  }

  .autocomplete-result:hover {
    background-color: #4AAE9B;
    color: white;
  }

  .register-button {
  display: inline-block;
  border-radius: 5px;
  background-color: #005229;
  border: none;
  color: #FFFFFF;
  text-align: center;
  font-size: 15px;
  padding: 10px;
  width: 160px;
  margin-top: 20px;
  transition: all 0.5s;
}

.register-button span {
  display: inline-block;
  position: relative;
  transition: 0.5s;
}

.register-button span:after {
  content: '\00bb';
  position: absolute;
  opacity: 0;
  top: 0;
  right: -20px;
  transition: 0.5s;
}

.register-button:hover span {
  padding-right: 25px;
  cursor: pointer;
}

.register-button:hover span:after {
  opacity: 1;
  right: 0;
}

input {
    margin: 5px;
    border-radius: 3px;
    padding: 4px;
}
</style>