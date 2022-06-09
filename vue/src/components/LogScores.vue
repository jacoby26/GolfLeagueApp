<template>
    <div>
        <div id="intro">
          <h1>Log Match Scores</h1>
        </div>
        <form v-on:submit.prevent="submitForm()">
            <label for="round">Round: </label>
            <select id="round" class="RoundList" v-model="round.selected_round">
                <option v-for="round in $store.state.rounds" 
                :value="round.teeTimeID" 
                v-bind:key="round.teeTimeID">
                Round: {{round.teeTimeID}} 
                Tee Time: {{round.teeTime}}
                </option>
            </select>
            <div>
                <!-- choose player id -->
            <label for="player">Player: </label>
            <select id="player" class="GolferList" v-model="round.selected_user">
                <option v-for="user in $store.state.member_golfers" 
                :value="user" 
                v-bind:key="user.user_id">
                  {{user}}
                </option>
            </select>
            </div>
            <div>
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
</style>