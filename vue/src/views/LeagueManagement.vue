<template>
  <div class="LeagueToolbox">
      <div id="leaguetoolselector">
        <LeagueSelector />
      <ul id="league-options-menu">
        <li id="league-options" v-on:click="show('Scores')">Report Scores</li>
        <li id="league-options-middle" v-on:click="show('Arrange')">Create Match</li>
        <li id="league-options" v-on:click="show('Members')">Manage Members</li>
      </ul>
    </div>
    <div id="league-name-location">
      <h1 id="league-title" v-if="this.$store.state.currentLeague.name != ''">{{ this.$store.state.currentLeague.name }}</h1>
      <h1 id="league-title" v-if="this.$store.state.currentLeague.name == ''" >Please Select League</h1>
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
  padding: 5px 5px 5px 0;
  display: grid;
  grid-template-columns: 1fr 3fr;
  grid-template-rows: auto 1fr;
  grid-template-areas: 
  "selector league"
  "selector details";
}
#leaguetoolselector{
  grid-area: selector;
  background-color: rgba(255, 255, 255, 0);
  margin: 10px auto auto auto;
  display: flex;
  flex-direction: column;
  width: 100%;
}
#leaguetoolselector ul{
  list-style-type: none;
}
#selected-league-tool{
  grid-area: details;
  display: flex;
  justify-content: center;
  border-left: 5px #005229 solid;
}
#selected-league-tool h2{
  text-align: center;
  
}
#league-name-location{
  grid-area: league;
  display: flex;
  justify-content: center;
  font-size: 28px;
  border-left: 5px #005229 solid;
}
#league-title {
  margin: 15px auto auto auto;
text-decoration: underline;
}
#league-options-menu{
display: flex;
flex-direction: column;
padding: 0;
cursor: pointer;
align-items: center;

}
#league-options{
  border-top: black solid 1px;
  border-bottom: black solid 1px;
}

</style>