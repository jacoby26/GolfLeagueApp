<template>
  <div>
    <h1>{{$store.state.currentRound.leagueName}}, {{$store.state.currentRound.date}} {{$store.state.currentRound.teeTime}}
    </h1>
    <div v-if="isScored">
      <h2>Personal Score for this Round: {{$store.state.currentRound.score}}</h2>
    </div>
    <div v-if="isSoonEnoughForForecast">
<!--Plug data from weather API call here-->
    </div>
  </div>
</template>

<script>
export default {
computed:{
  isSoonEnoughForForecast(){
    //Check if match is within 8 days, May need another conditional for hourly forecast
    return false;
  },
  isScored(){
    return this.$store.state.currentRound.score > 0;
  }
}
}
</script>

<style>

</style>