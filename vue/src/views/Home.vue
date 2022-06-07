<template>
  <div class="home">
    <Details id="details" v-bind:item="item" />
    <div id="selector">
      <ul>
        <li v-for="league in $store.state.leagues" v-bind:key="league.leagueID" v-on:click="show(league.leagueID, 'league')">{{league.name}}</li>
        <li v-for="round in $store.state.rounds" v-bind:key="round.roundID" v-on:click="show(round.teeTimeID, 'game')"> {{round.date}} {{round.teeTime}} </li>
      </ul>
    </div>
  </div>
  
</template>


<script>
import Details from "./Details.vue";
import LeagueService from "../services/LeagueService.js";
export default {
  name: "home",
  data(){
    return{
      item:''
    }
  },
  components: {
    Details
  },
  created(){
      LeagueService.viewLeagues(this.$store.state.user).then(
        (leagues) => {
            this.$store.commit('EMPTY_LEAGUES');
            leagues.data.forEach(league => {
              this.$store.commit('LOAD_LEAGUE', league);
              });
        }
      )
      LeagueService.viewAllRounds(this.$store.state.user).then(
      (games) => {
        this.$store.commit('EMPTY_ROUNDS');
        games.data.forEach(round =>{
          this.$store.commit('LOAD_ROUNDS', round);
        })
    })
  },
  methods:{
    show(input, type){
      this.item = type;
      if(this.item === 'league'){
        this.$store.commit('FETCH_LEADERBOARD', input);
        }
      else if(this.item  === 'game'){
        this.$store.commit('FETCH_ROUND', input);

      }
    }
  }
};
</script>
<style>
.home{
  margin: 5px;
  background-color: rgba(186,199,202,.9);
  border-radius: 5px;
  padding: 5px;
  display: grid;
  grid-template-columns: 1fr 3fr;
  grid-template-areas: "selector details";
}
#selector{
  grid-area: selector;
  background-color: rgba(255, 255, 255, 0);
}
#selector ul{
  list-style-type: none;
}
#details{
  padding: 5px;
  grid-area: details;
}
</style>
