<template>
  <div>
      <div id="add-round-body">
        <h1>Schedule New Round</h1>
        <form v-on:submit.prevent="submitForm()" class="add-round-form">
                <label class="tee_time_label" for="tee_time">Tee Time</label>
                <input class="tee_time_input" id="tee_time" type="time" v-model="round.teeTime" defaultValue="08:00" autocomplete="off" />
            
                <label class="date_label" for="date">Date</label>
                <input class="date_input" id="date" type="date" v-model="round.date" autocomplete="off" />
                        
                <button class="register-button"><span>Submit</span></button>
                <button id="cancel-register" class="register-button" v-on:click.prevent="cancelForm" type="cancel"><span>Cancel</span></button>
            
        </form>
        
    </div>
  </div>
</template>

<script>
import LeagueService from '../services/LeagueService.js'

export default 
{
    name: "add-round-form",
    data(){
      return {
        round: {
            teeTime: "",
            date: "",
            league: this.$store.state.currentLeague
            },
      }
    },
    methods: 
    {
        submitForm() 
        {
          let newRound =
          {
            teeTime: this.round.teeTime,
            date: this.round.date,
            leagueID: this.$store.state.currentLeague.ID
            // league: this.$store.state.currentLeague
          }
             console.log(newRound)
          LeagueService
          .addRound(newRound)
       
          .then(response => 
          {
            if (response.status === 200) 
            {
              window.alert("Round added successfully!");
            } else 
            {
              window.alert("Problem adding new round")
            }
          })
        }
            
    }
}
</script>
<style>
.add-round-form {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    
}

.tee_time_label,
.date_label {
    display: flex;
    justify-content: center;
    font-weight: bold;
    font-size: 27px;
} 
.tee_time_input, 
.date_input {
    display: flex;
    justify-content: center;
    width: 35%;
    margin: 1%;
    padding: 6px;
}
/* #buttons {
    display: flex;
    flex-direction: row;
    justify-content: center;
} */
/* .btn-btn-submit, 
.btn-btn-cancel {
    display: flex;
    flex-direction: row;
    justify-content: center;
    margin: 1%;
    padding: 1%;
    width: 8%;
    font-size: 20px;
    background: url(/img/Home.3cf008bd.png) no-repeat;
    box-shadow: 0 1px 2px black;
    cursor: pointer;
} */
.add-round-form>.register-button {
  display: inline-block;
  border-radius: 5px;
  background-color: #005229;
  border: none;
  color: #FFFFFF;
  text-align: center;
  font-size: 15px;
  padding: 10px;
  width: 160px;
  margin-top: 15px;
  transition: all 0.5s;
  text-shadow: 1px 1px black;
}

.register-button#cancel-register {
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