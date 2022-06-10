<template>
  <div>
      <div id="add-round-body">
        <h1>Schedule New Round</h1>
        <form v-on:submit.prevent="submitForm()" class="add-round-form">
                <label class="tee_time_label" for="tee_time">Tee Time</label>
                <input class="tee_time_input" id="tee_time" type="time" v-model="round.teeTime" defaultValue="08:00" autocomplete="off" />
            
                <label class="date_label" for="date">Date</label>
                <input class="date_input" id="date" type="date" v-model="round.date" autocomplete="off" />
                        
                <button id="submit-register" class="btn-btn-submit"><span>Submit</span></button>
                <button id="cancel-register" class="btn-btn-cancel" v-on:click.prevent="cancelForm" type="cancel"><span>Cancel</span></button>
            
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

.add-round-form>button {
  display: inline-block;
  text-align: center;
  font-size: 15px;
  padding: 10px;
  width: 160px;
  margin-top: 15px;
  transition: all 0.5s;
}
</style>