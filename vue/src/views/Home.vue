<template>
  <div class="home">
    <Details id="details" v-bind:item="item" />
    <div id="selector">
      <ul>
        <li v-for="league in $store.state.leagues" v-bind:key="league.leagueID" v-on:click="show(league.leagueID, 'league')">{{league.name}}</li>
        <li v-for="round in $store.state.rounds" v-bind:key="round.roundID" v-on:click="show(round.teeTimeID, 'game')"> {{round.date}} {{round.teeTime}} </li>
      </ul>
    </div>
  </div>
  
</template>


<script>
import Details from "./Details.vue";
import LeagueService from "../services/LeagueService.js";
import CourseService from "../services/CourseService.js";
export default {
  name: "home",
  data(){
    return{
      item:''
    }
  },
  components: {
    Details
  },
  created(){
    LeagueService.viewLeagues(this.$store.state.user).then(
      (leagues) => {
        this.$store.commit('EMPTY_LEAGUES');
        leagues.data.forEach(league => {
          this.$store.commit('LOAD_LEAGUE', league);
        });
      }
    )
    LeagueService.viewAllRounds(this.$store.state.user).then(
      (games) => {
        this.$store.commit('EMPTY_ROUNDS');
        games.data.forEach(round =>{
          this.$store.commit('LOAD_ROUNDS', round);
        })
      }
    )
    LeagueService.viewInvites(this.$store.state.user).then(
      (fetch_invites) => {
        fetch_invites.data.forEach(invite => {
          this.$store.commit('LOAD_INVITES', invite);
        })
        this.$App.$forceUpdate();
      }
    )
  },
  methods:{
    show(input, type){
      this.item = type;
      if(this.item === 'league'){
        this.$store.commit('FETCH_LEADERBOARD', input);
        }
      else if(this.item  === 'game'){
        this.$store.commit('FETCH_ROUND', input);
        let year = this.$store.state.currentRound.date.substring(0,4);
        let month = this.$store.state.currentRound.date.substring(5,7);
        month--;
        let day = this.$store.state.currentRound.date.substring(8);
        let then = new Date(year, month, day);
        let elapsed = new Date;
        elapsed.setTime(then.getTime()-Date.now());
        if(1 <= elapsed.getDate <= 9){
          CourseService.checkcourse(this.$store.state.currentRound.courseId).then(
            (course) => {
              fetch(`http://api.openweathermap.org/data/3.0/onecall?lat=${course.data.latitude}&lon=${course.data.longitude}&units=imperial&appid=f10f0d0374017f990077648ef4d578bc`).then(
                (response) => response.json()
                .then(data => ({
                  data: data
                })
                ).then (body => { 
                this.$store.commit('SET_WEATHER', body.data);
                })
              )
            }
          )
        }
      }
    },
  }
};
</script>
<style>
.home{
  margin: 5px;
  background-color: rgba(186,199,202,.9);
  border-radius: 5px;
  padding: 5px;
  display: grid;
  grid-template-columns: 1fr 3fr;
  grid-template-areas: "selector details";
}
#selector{
  grid-area: selector;
  background-color: rgba(255, 255, 255, 0);
  border-right: 5px #005229 double;
}
#selector ul{
  list-style-type: none;
  width: 60%;
}
#selector li{
  padding-bottom: 3px;
  font-size: 17px;
}
#selector li:hover{
  border: outset;
  padding: 5px 10px;
  cursor: pointer;
}
#details{
  padding: 5px;
  grid-area: details;
}
</style>
