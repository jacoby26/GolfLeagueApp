<template>
  <div>
      <div id="add-round-body">
        <h1>Schedule New Round</h1>
        <form v-on:submit.prevent="submitForm()" class="add-round-form">
                <label class="tee_time_label" for="tee_time">Tee Time</label>
                <input class="tee_time_input" id="tee_time" type="time" v-model="round.teeTime" defaultValue="08:00" autocomplete="off" />
            
                <label class="date_label" for="date">Date</label>
                <input class="date_input" id="date" type="date" v-model="round.date" autocomplete="off" />
                        
                <button class="btn-btn-submit">Submit</button>
                <button class="btn-btn-cancel" v-on:click.prevent="cancelForm" type="cancel">Cancel</button>
            
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
            // league: this.$store.state.currentLeague
            },
      }
    },
    methods: 
    {
        submitForm() 
        {
          let newRound = [
          {
            teeTime: this.round.teeTime,
            date: this.round.date,
            // league: this.$store.state.currentLeague
          },
          this.$store.state.currentLeague
          ]    
          LeagueService
          .addRound(newRound)
          // console.log(this.$store.league)
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
.btn-btn-submit, 
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
}
</style>