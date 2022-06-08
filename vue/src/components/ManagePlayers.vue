<template>
  <div>
      <h1>Message Invite or Remove players</h1>
      <h2>Select players to invite:</h2>
      <form @submit.prevent="SendInvites">
        <select class="GolferList" multiple v-model="selected_users">
          <option v-for="user in $store.state.non_member_golfers" v-bind:key="user.user_id">
            {{user}}
          </option>
        </select>
      <button>Invite to {{$store.state.currentLeague.name}}</button>
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
    }
  },
  // created:{
  // }
}
</script>

<style>
.GolferList{
  min-width: 350px;
}
</style>