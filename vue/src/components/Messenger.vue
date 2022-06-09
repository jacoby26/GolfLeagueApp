<template>
<div id="messenger">
  <div v-for="invite in $store.state.invites" :key="invite.inviteId">
  <p>You have been invited to {{invite.leagueName}} run by {{invite.leagueAdmin}}, the league plays on {{invite.courseName}} at {{invite.courseAddress}}.</p>
  <button v-on:click="Accept(invite)">Accept</button><button v-on:click="Reject(invite)">Decline</button>
  </div>
</div>
</template>

<script>
import LeagueService from "../services/LeagueService.js";
export default {
  props:[
    "invites"
  ],
  model:{
    prop: "invites",
    event: 'removeinvite'
  },
  computed:{
    invitesLocal:{
      get: function() {
        return this.invites;
      },
      set: function(input) {
        this.$emit('removeinvite', input);
      }
    }
  },
// created(){
//   LeagueService.viewInvites(this.$store.state.user).then(
//     (fetch_invites) => {
//       fetch_invites.data.forEach(invite => {
//         this.invites.push(invite);
//       });
//     }
//   )
// },
methods:{
  Accept(invite){
    this.$store.state.currentInvite = invite;
    this.$store.state.currentInvite.accepted = true;
    LeagueService.ActionInvites(this.$store.state.currentInvite);
    this.$store.commit('REMOVE_INVITE');
    this.$forceUpdate();
    this.App.$forceUpdate();

  },
  Reject(invite){
    this.$store.state.currentInvite = invite;
    LeagueService.ActionInvites(this.$store.state.currentInvite);
    this.$store.commit('REMOVE_INVITE');
    this.$forceUpdate();
//    this.invites = this.invites.filter(invitation => {
//      return invitation != invite
//    })
this.vm.$forceUpdate();
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