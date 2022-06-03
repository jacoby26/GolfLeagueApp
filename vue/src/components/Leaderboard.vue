<template>
<div id="Leaderboard">
  <h1>{{league.name}} Leaderboard</h1>
    <table>
      <tr>
        <td>Ranking</td>
        <td>Name</td>
        <td>Score</td>
      </tr>
      <tr v-for="golfer in league.leaderboardTable" v-bind:key="golfer.id">
        <td>{{golfer.rank}}</td>
        <td>{{golfer.name}}</td>
        <td>{{golfer.score}}</td>
      </tr>
    </table>
    </div>
</template>

<script>
export default {
computed:{
    league(){
      return this.$store.commit('FETCH_LEADERBOARD', this.element);
    }
  },
props:["element"],
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
</style>