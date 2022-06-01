<template>
  <div class="home">
    <Details id="details" v-bind:item="item"/>
    <div id="selector">
      <h3>When clicked, the items below will change this--></h3>
      <ul>
        <li v-on:click="show(Leaderboard)">Leaderboard</li>
        <li v-for="game in matches" v-bind:key="game.id" v-on:click="show(game)"> {{game.date}} </li>
      </ul>
    </div>
  </div>
</template>

<script>
import Details from "../views/Details.vue";
import LeagueService from "../services/LeagueService.js";

export default {
  name: "home",
  data(){
    return{
      Matches: [],
      Leagueid:'',
      item:{}
    }
  },
  components: {
    Details
  },
  created(){
    LeagueService.viewRounds().then(
      (Games) => {
        this.Matches = Games.data
      }
    );
    //this.Leagueid = this.$Store.state.user;
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
  background-image: url('../img/Home.png');
  background-repeat: no-repeat;
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