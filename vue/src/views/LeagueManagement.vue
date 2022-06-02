<template>
  <div class="LeagueToolbox">
      <div id="leaguetoolselector">
      <h3>When clicked, the items below will change this--></h3>
      <ul>
        <li v-on:click="show('Scores')">Report Scores</li>
        <li v-on:click="show('Arrange')">Arrange Games</li>
        <li v-on:click="show('Members')">Manage Members</li>
      </ul>
    </div>
    <div id="selected-league-tool">
        <ManagePlayers v-if="currentDisplay === 'Members' "/>
        <LogScores v-if="currentDisplay === 'Scores' "/>
        <ArrangeGames v-if="currentDisplay === 'Arrange' "/>
    </div>
  </div>
</template>

<script>
    import LogScores from "../components/LogScores.vue";
    import ManagePlayers from "../components/ManagePlayers.vue";
    import ArrangeGames from "../components/ArrangeMatches.vue";
export default {
data(){
    return{
        currentDisplay: 'Members'
    }
},
methods:{
    show(toShow){
        this.currentDisplay = toShow;
    }
},
components:{
    LogScores,
    ManagePlayers,
    ArrangeGames
}
}
</script>

<style>
.LeagueToolbox{
  margin: 5px;
  background-color: rgba(186,199,202,.9);
  padding: 5px;
  display: grid;
  grid-template-columns: 1fr 3fr;
  grid-template-areas: "selector details";
}
#leaguetoolselector{
  grid-area: selector;
  background-color: rgba(255, 255, 255, 0);
}
#leaguetoolselector ul{
  list-style-type: none;
}
#selected-league-tool{
  padding: 5px;
  grid-area: details;
}
</style>