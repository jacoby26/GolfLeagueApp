<template>
    <div>
        <form v-on:submit.prevent="submitForm">
            <label for="course">League: </label>
            <input v-model="search"  @input="onChange" type="text"/>
                <ul  v-show="isOpen" class="autocomplete-results">
                <li class="autocomplete-result" v-for="league in filteredResults" 
            :key="league.leagueID" @click="setResult(league)"> {{ league.name }} </li>
                </ul>
            <button type="submit">Select League</button>
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