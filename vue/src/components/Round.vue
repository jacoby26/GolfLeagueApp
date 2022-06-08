<template>
  <div>
    <h1>{{$store.state.currentRound.leagueName}}, {{$store.state.currentRound.date}} {{$store.state.currentRound.teeTime}}
    </h1>
    <div v-if="isScored">
      <h2>Personal Score for this Round: {{$store.state.currentRound.score}}</h2>
    </div>
    <div v-if="EightDayForecast">
      {{$store.state.forecast.daily[this.TimeUntilRound()].weather[0].description}}
      <p>Temperature: {{$store.state.forecast.daily[this.TimeUntilRound()].temp.day}}</p>
      <p>Feels Like: {{$store.state.forecast.daily[this.TimeUntilRound()].feels_like.day}}</p>
      <p>Wind Speed: {{$store.state.forecast.daily[this.TimeUntilRound()].wind_speed}}</p>
    </div>
    <div v-if="HourlyForecast">
      {{$store.state.forecast.hourly.weather.description}}
      <p>Temperature: {{$store.state.forecast.hourly.temp.day}}</p>
      <p>Feels Like: {{$store.state.forecast.hourly.feels_like.day}}</p>
      <p>Wind Speed: {{$store.state.forecast.hourly.wind_speed}}</p>
    </div>
  </div>
</template>

<script>
export default {
computed:{
  EightDayForecast(){
    return 8>= this.TimeUntilRound() >= 2;
  },
  isScored(){
    return this.$store.state.currentRound.score > 0;
  },
  HourlyForecast(){
    //Check if match is within 48 hours
    return false
  }
},
methods:{
  TimeUntilRound(){
        let year = this.$store.state.currentRound.date.substring(0,4);
        let month = this.$store.state.currentRound.date.substring(5,7);
        month--;
        let day = this.$store.state.currentRound.date.substring(8);
        let then = new Date(year, month, day);
        let elapsed = new Date;
        elapsed.setTime(then.getTime()-Date.now());
        console.log(elapsed.getDate());
        return elapsed.getDate() -1;
  }
}
}
</script>

<style>

</style>