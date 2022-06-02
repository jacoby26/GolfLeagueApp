<template>
  <div class="home">
    <Details id="details" v-bind:item="item"/>
    <div id="selector">
      <h3>When clicked, the items below will change this--></h3>
      <ul>
        <li v-for="league in Leagues" v-bind:key="league.leagueID" v-on:click="show(league)">{{league.name}}</li>
        <li v-for="game in Matches" v-bind:key="game.Id" v-on:click="show(game)"> {{game.Date}} </li>
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
      Matches: [],
      Leagues:[],
      item:{}
    }
  },
  components: {
<<<<<<< HEAD
    Details
=======
    Details,
<<<<<<< HEAD
=======
    AddCourseForm,
    LogScores
>>>>>>> main
>>>>>>> 9c2c2d85e69c2e0c0d2ecc1cc11b37a6b76734b7
  },
  created(){
    LeagueService.viewLeagues().then(
      (leagues) => {
        this.Leagues = leagues.data;
      }
    )
    LeagueService.viewRounds().then(
      (games) => {
      this.Matches = games.data;
    })
  },
  methods:{
    show(item){
      return this.item = item;
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
