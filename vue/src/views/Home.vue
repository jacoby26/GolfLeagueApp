<template>
  <div class="home">
    <Details id="details" v-bind:item="item" />
    <div id="selector">
      <h3>When clicked, the items below will change this--></h3>
      <ul>
        <li v-for="league in $store.state.leagues" v-bind:key="league.leagueID" v-on:click="show(league.leagueID, 'league')">{{league.name}}</li>
       <!-- <li v-for="game in Matches" v-bind:key="game.Id" v-on:click="show(game, 'game')"> {{game.Date}} </li> -->
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
//    LeagueService.viewRounds().then(
//      (games) => {
//      this.Matches = games.data;
//    })
  },
  methods:{
    show(input, type){
      this.item = type;
      if(this.item === 'league'){
        this.$store.commit('FETCH_LEADERBOARD', input);
        }
      else if(type === 'game'){
        this.item = 'round';

      }
    }
  }
};
</script>
<style>
.home{
  margin: 5px;
  background-color: rgba(186,199,202,.9);
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
