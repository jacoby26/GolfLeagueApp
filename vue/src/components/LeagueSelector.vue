<template>
    <div>
        <form id="select-league-form" v-on:submit.prevent="submitForm">
            <label id="select" for="course">Select League</label>
            <input class="selector" v-model="search"  @input="onChange" type="text"/>
                <ul  v-show="isOpen" class="autocomplete-results">
                <li class="autocomplete-result" v-for="league in filteredResults" 
            :key="league.leagueID" @click="setResult(league)"> {{ league.name }} </li>
                </ul>
            <button type="submit" class="register-button"><span>Select</span></button>
        </form>
    </div>
</template>

<script>
import leagueService from "../services/LeagueService.js"
export default {
    data(){
        return{
            search: '',
            leagues: [],
            filteredResults: [],
            isOpen: false,
            league:{
                leagueID: '',
                name: '',
                organizer: this.$store.state.user,
                courseID: '',
                players:[this.$store.state.user]
            }
        }
    },
    methods: {
        handleClickOutside(event) {
            if (!this.$el.contains(event.target)) {
                this.isOpen = false;
            }
        },
        setResult(result) {
            this.search = result.name
            this.league = result;
            this.isOpen = false;
        },
        filterResults() {
            this.filteredResults = this.leagues.filter(league => league.name.toLowerCase().indexOf(this.search.toLowerCase()) > -1);
        },
        onChange() {
            this.filterResults();
            this.isOpen = true;
        },
        getAllLeaguesForMenu() {
            leagueService.viewManagedLeagues(this.$store.state.user).then((response) => {
                this.leagues = response.data
            })
        },
        submitForm() {
            const newLeague = {
                ID: this.league.leagueID,
                name: this.league.name,
                organizer: this.$store.state.user,
                courseID: this.league.courseID,
            };
            
            this.$store.state.currentLeague = newLeague
            leagueService.getMembers(this.league.leagueID).then(results =>
            {
                this.$store.state.member_golfers = results.data;
            });
            leagueService.getNonMembers(this.$store.state.currentLeague.ID).then(results =>
            {
                this.$store.state.non_member_golfers = results.data;
            });
        },
    },
    mounted() {
       this.getAllLeaguesForMenu()
       document.addEventListener('click', this.handleClickOutside);
    },
}
</script>

<style scoped>
    #league-select {
    border-radius: 3px;
    padding: 4px;
}
    .autocomplete {
    position: relative;
  }

  .autocomplete-results {
    border: 1px solid black;
    height: 120px;
    min-height: 1em;
    max-height: 6em;    
    overflow: auto;
    width: 75%;
    border-radius: 3px;
    padding: 4px;
    margin-top: 2px;
    border: 3px solid #005229;
    border-radius: 5px;
    background-color: #ffffff;
    opacity: 50%;
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
#select {
    font-size: 24px;
    text-align: center;
}
#select-league-form {
    display: flex;
    flex-direction: column;
    align-items: center;
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
  width: 180px;
  margin-top: 5px;
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
input.selector {
    margin: 5px;
    border-radius: 3px;
    padding: 4px;
    background: white;
    width: 75%;
}
</style>