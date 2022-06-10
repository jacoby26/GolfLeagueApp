<template>
    <div id="log-score">
        <div id="intro">
          <h1>Log Match Scores</h1>
        </div>
        <form v-on:submit.prevent="submitForm()" id="score-form">
            <div class="round">
            <label for="round">Round: </label>
            <select id="round" class="RoundList" v-model="round.selected_round">
                <option v-for="round in $store.state.rounds" 
                :value="round.teeTimeID" 
                v-bind:key="round.teeTimeID">
                {{round.date}} {{round.teeTime}}
                </option>
            </select>
            </div>
            <div class="player">
                <!-- choose player id -->
            <label for="player">Player: </label>
            <select id="player" class="playerList" v-model="round.selected_user">
                <option v-for="user in $store.state.member_golfers" 
                :value="user" 
                v-bind:key="user.user_id">
                  {{user}}
                </option>
            </select>
            </div>
            <div class="score">
                <!-- post to round id where  -->
                <label for="score">Score: </label>
                <input id="score" type="text" v-model="round.score" autocomplete="off" />
            </div>
            <button class="register-button"><span>Submit</span></button>
            <button id="cancel-score" class="register-button" v-on:click.prevent="cancelForm" type="cancel"><span>Cancel</span></button>
        </form>
    </div>
</template>

<script>
import leagueService from "../services/LeagueService.js"

export default {
    name: "report-score-form",
    data(){
      return {
        round: {
            selected_round: '',
            selected_user: '',
            score: ''
            },
      }
    },
    methods: {
        submitForm() {
            const newScore = {
                round: this.round.selected_round,
                user: this.round.selected_user,
                score: this.round.score,
            };

            leagueService
            .reportRound(newScore)
            .then(response => {
                if (response.status === 200) {
                    window.alert("Score added successfully!");
                } else {
                    window.alert("Problem adding score")
                }
            })
        }
    }
}
</script>
<style>
#score-form{
  display: flex;
  flex-direction: column;
}
#score-form:not(#round), 
#score-form:not(#player), 
#score-form:not(#score), 
#score-form>button{
  display: flex;
  align-self: center;
  justify-content: center;
}
#score-form>button{
    width: 50%;
    margin-top: 15px;
}
#log-score{
    display: flex;
    flex-direction: column;
    align-items: center;
}
#intro{
    padding: 0;
}

.round, 
.player,
.score{
    padding: 2px;
}

.round>label, 
.player>label,
.score>label{
    font-size: 24px;
}
.round>select, 
.player>select,
.score>input{
    font-size: 18px;
    border-radius: 3px;
    background: white;
    min-width: 70%;
}

.register-button {
  display: inline-block;
  border-radius: 5px;
  background-color: #005229;
  border: none;
  color: #FFFFFF;
  text-align: center;
  font-size: 15px;
  padding: 10px;
  width: 160px;
  margin-top: 5px;
  transition: all 0.5s;
  text-shadow: 1px 1px black;
  width: auto;
}

.register-button#cancel-score {
  background-color: #ffb81f;
}

.register-button span {
  display: inline-block;
  position: relative;
  transition: 0.5s;
}

.register-button span:after {
  content: '\00bb';
  position: absolute;
  opacity: 0;
  top: 0;
  right: -20px;
  transition: 0.5s;
}

.register-button:hover span {
  padding-right: 25px;
  cursor: pointer;
}

.register-button:hover span:after {
  opacity: 1;
  right: 0;
}

form {
  border-radius: 3px;
}
</style>