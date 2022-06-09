<template>
  <div class="LeagueToolbox">
      <div id="leaguetoolselector">
        <LeagueSelector />
      <h3>{{ this.$store.state.currentLeague.name }}</h3>
      <ul>
        <li v-on:click="show('Scores')">Report Scores</li>
        <li v-on:click="show('Arrange')">Create Match</li>
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
    import LeagueSelector from "../components/LeagueSelector.vue";
    import LogScores from "../components/LogScores.vue";
    import ManagePlayers from "../components/ManagePlayers.vue";
    import ArrangeGames from "../components/ArrangeMatches.vue";
export default {
data(){
    return{
        currentDisplay: 'Members',
        leagueDisplay: this.$store.state.currentLeague.name
    }
},
methods:{
    show(toShow){
        this.currentDisplay = toShow;
        console.log(this.$store.state.currentLeague.name)
        console.log(this.leagueDisplay)
    }
},
components:{
    LogScores,
    ManagePlayers,
    ArrangeGames,
    LeagueSelector
}
}
</script>

<style>
.LeagueToolbox{
  margin: 5px;
  background-color: rgba(186,199,202,.9);
  border-radius: 5px;
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
  display: flex;
  justify-content: center;
}
#selected-league-tool h2{
  text-align: center;
}
</style>