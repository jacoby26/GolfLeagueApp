<template>
<div id="Leaderboard">
  <h1>{{league.name}} Leaderboard</h1>
    <table>
      <tr id="LeaderboardHeaders">
        <td class="rankColumn">Rank</td>
        <td class="nameColumn">Name</td>
        <td class="scoreColumn">Score</td>
      </tr>
      <tr v-for="golfer in league.leaderboardTable" v-bind:key="golfer.id">
        <td class="rankColumn">{{golfer.rank}}</td>
        <td class="nameColumn">{{golfer.name}}</td>
        <td class="scoreColumn">{{golfer.score}}</td>
      </tr>
    </table>
    </div>
</template>

<script>
export default {
computed:{
    league(){
      return this.$store.state.currentLeague;
    }
  },
}
</script>

<style>
#Leaderboard h1{
  text-align: center;
}
#Leaderboard {
  display: flex;
  flex-direction: column;
  align-items: center;
}
#Leaderboard tr{
  font-size: 18pt;
}
#Leaderboard table, tr, td{
  border: 1px solid black;
  padding: 1%;
}
#Leaderboard table{
  width: 50%;
}
.rankColumn{
  width: 1fr;
}
#LeaderboardHeaders{
}
.nameColumn{
  width: 3fr;
}
.scoreColumn{
  width: 1fr;
}
</style>