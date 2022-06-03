<template>
  <div class="home">
    <Details id="details" v-bind:item="item" v-bind:element="element"/>
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
      element: 0,
      item:''
    }
  },
  components: {
    Details
  },
  created(){
    if(this.$store.state.leagues.length === 0){
      LeagueService.viewLeagues(this.$store.state.user).then(
        (leagues) => {
          leagues.data.forEach(league => {
            this.$store.commit('LOAD_LEAGUE', league);
 //           LeagueService.viewStandings(league.leagueID).then(
 //             (table) => {
 //               this.$store.commit('POPULATE_LEAGUE', league.leagueID, table);
 //     }
 //   )
        });
      }
    )
//    LeagueService.viewRounds().then(
//      (games) => {
//      this.Matches = games.data;
//    })
}
  },
  methods:{
    show(input, type){
      this.item = type;
      if(this.item === 'league'){
        let league = this.$store.state.leagues.filter((leagues) =>{
          return leagues.leagueId === input;
        });
        this.element = league.leagueID;
//        LeagueService.viewStandings(this.item.leagueID).then(
//      (rankings) => {
//        this.item.rankings = rankings.data;
//      }
//    )
      } else if(type === 'game'){
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
