<template>
<div id="messenger" v-if="invites.size>0">
  <div v-for="invite in invites" :key="invite.invite_Id">
  <p>You have been invited to {{invite.leagueName}} run by {{invite.leagueAdmin}}, the league plays on {{invite.courseName}} at {{invite.courseAddress}}.</p>
  <button v-on:click="Accept(invite)">Accept</button><button v-on:click="Reject(invite)">Decline</button>
  </div>
</div>
</template>

<script>
import LeagueService from "../services/LeagueService.js";
export default {
  data(){
    return{
      invites: []
    }
  },
created(){
  LeagueService.viewInvites(this.$store.state.user).then(
    (fetch_invites) => {
      fetch_invites.data.forEach(invite => {
        this.invites.push(invite);
      });
    }
  )
},
methods:{
  Accept(invite){
    this.$store.state.currentInvite = invite;
    this.$store.state.currentInvite.accepted = true;
    LeagueService.ActionInvites(this.$store.state.currentInvite);
    this.invites = this.invites.filter(invitation => {
      return invitation != invite
    })
    this.$forceUpdate();
    this.App.$forceUpdate();

  },
  Reject(invite){
    this.$store.state.currentInvite = invite;
    LeagueService.ActionInvites(this.$store.state.currentInvite);
    this.invites = this.invites.filter(invitation => {
      return invitation != invite
    })
    this.$forceUpdate();
  }
}
}
</script>

<style>
#messenger{
  margin: 5px;
  margin-left: 0px;
  background-image: url('../img/Home.png');
  background-repeat: no-repeat;
  padding: 5px;
  color: #ffb81f;
  border-radius: 5px;
}
</style>