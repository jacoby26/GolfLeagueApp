<template>
  <div id="invite-tool">
      <h2>Select players to invite:</h2>
      <form @submit.prevent="SendInvites" id="invite-tool">
        <select class="GolferList" multiple v-model="selected_users">
          <option v-for="user in $store.state.non_member_golfers" v-bind:key="user.user_id">
            {{user}}
          </option>
        </select><br>
      <button id="invite-button" class="register-button"><span>Invite to {{$store.state.currentLeague.name}}</span></button>
      </form>
  </div>
</template>

<script>
import LeagueService from "../services/LeagueService.js";
export default {
  data(){
    return{
      users:[],
      selected_users:[],
      invites:[]
    }
  },
  methods: {
    SendInvites() {
      this.selected_users.forEach(user => {
      this.invites.push({
        accepted:false,
        courseAddress:"",
        courseName:"",
        inviteId: '',
        leagueAdmin: user,
        leagueId: this.$store.state.currentLeague.ID,
        leagueName:"",
        userId: 0
        });
        })
      this.invites.forEach(invite => {
        LeagueService.AddInvites(invite);
      });
      alert("Invitations sent!");
    }
  },
  // created:{
  // }
}
</script>

<style>
#invite-tool{
  display: flex;
  flex-direction: column;
  align-items: center;
}
.GolferList{
  min-width: 350px;
  margin: 0;
  border: 1px solid black;
  height: 250px;
  min-height: 1em;
  max-height: auto;    
  overflow: auto;
  width: 75%;
  background: white;
  border-radius: 3px;
  padding: 4px;
}
.GolferList>option:checked,
.GolferList>option:hover{
  background-color: #4AAE9B;
  color: white;
}
.register-button#invite-button {
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