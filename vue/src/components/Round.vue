<template>
  <div>
    <h1>{{$store.state.currentRound.leagueName}}, {{$store.state.currentRound.date}} {{$store.state.currentRound.teeTime}}
    </h1>
    <div v-if="isScored">
      <h2>Personal Score for this Round: {{$store.state.currentRound.score}}</h2>
    </div>
    <div v-if="EightDayForecast">
      {{$store.state.forecast.daily.weather.description}}
      Temperature: {{$store.state.forecast.daily.temp.day}}
      Feels Like: {{$store.state.forecast.daily.feels_like.day}}
      Wind Speed: {{$store.state.forecast.daily.wind_speed}}
    </div>
    <div v-if="HourlyForecast">
      {{$store.state.forecast.hourly.weather.description}}
      Temperature: {{$store.state.forecast.hourly.temp.day}}
      Feels Like: {{$store.state.forecast.hourly.feels_like.day}}
      Wind Speed: {{$store.state.forecast.hourly.wind_speed}}
    </div>
  </div>
</template>

<script>
export default {
computed:{
  EightDayForecast(){
    //Check if match is within 8 days, but more than 48 hours
    return false;
  },
  isScored(){
    return this.$store.state.currentRound.score > 0;
  },
  HourlyForecast(){
    //Check if match is within 48 hours
    return false
  }
}
}
</script>

<style>

</style>