<template>
<div>
  <h1>Current Leaderboard</h1>
    <table>
      <tr>
        <td>Name</td>
        <td>Score</td>
      </tr>
      <tr v-for="golfer in league" v-bind:key="golfer.id">
        <td>{{golfer.name}}</td>
        <td>{{golfer.Score}}</td>
      </tr>
    </table>
    </div>
</template>

<script>
export default {

}
</script>

<style>

</style>