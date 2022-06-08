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
            
            <button class="btn btn-submit">Submit</button>
            <button class="btn btn-cancel" v-on:click.prevent="cancelForm" type="cancel">Cancel</button>
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