<template>
  <div id="app" v-bind:class="{'logged-in': logged}">
    <div id="nav" v-if="logged">
      <img id="homebtn" v-bind:src='homebtn.image' @mouseover="homehover()" @mouseout="homeout()" v-on:click="homeclick()"/>
      <img id="addleaguebtn" v-bind:src='addleaguebtn.image' @mouseover="createhover()" @mouseout="createout()" v-on:click="createclick()"/>
      <img id="manageleaguebtn"  v-bind:src='manageleaguebtn.image' @mouseover="managehover()" @mouseout="manageout()" v-on:click="manageclick()"/>
      <img id="addcoursebtn" v-if="$store.state.user.username === 'admin'" v-bind:src='addcoursebtn.image' @mouseover="addhover()" @mouseout="addout()" v-on:click="addclick()"/>
      <img id="addmatchbtn" v-bind:src='addmatchbtn.image' @mouseover="matchhover()" @mouseout="matchout()" v-on:click="matchclick()"/>
      <img id="logoutbtn" v-bind:src='logoutbtn.image' @mouseover="logouthover()" @mouseout="logoutout()" v-on:click="logoutclick()" v-if="$store.state.token != ''"/>
    </div>
    <div class="mainBody">
    <router-view id="router-view"/>
    <Messenger v-show="areInvites && logged" v-bind:invites="invites"/>
    </div>
  </div>
</template>
<script>
import Messenger from "./components/Messenger.vue";
import HomeRestore from "./img/HomeRestore.png";
import HomeHover from "./img/HOH.png";
import HomeClick from "./img/HOC.png";
import CreateRestore from "./img/CLR.png";
import CreateHover from "./img/CLH.png";
import CreateClick from "./img/CLC.png";
import MatchRestore from "./img/RMR.png";
import MatchHover from "./img/RMH.png";
import MatchClick from "./img/RMC.png";
import ManageRestore from "./img/MLR.png";
import ManageHover from "./img/MLH.png";
import ManageClick from "./img/MLC.png";
import LogoutRestore from "./img/LOR.png";
import LogoutHover from "./img/LOH.png";
import LogoutClick from "./img/LOC.png";
import AddRestore from "./img/ACR.png";
import AddHover from "./img/ACH.png";
import AddClick from "./img/ACC.png";
export default {
  data() {
    return{
      homebtn:{image:HomeRestore},
      addleaguebtn:{image:CreateRestore},
      addmatchbtn:{image:MatchRestore},
      manageleaguebtn:{image:ManageRestore},
      logoutbtn:{image:LogoutRestore},
      addcoursebtn:{image:AddRestore},
      invites : []
    }
  },
  computed:{
    logged(){
      return this.$store.state.token != '';
    },
  areInvites(){
    return (this.$store.state.invites.length != 0);
  },
  },
  components:{
    Messenger,
  },
methods:{
  homehover(){
    this.homebtn.image = HomeHover
  },
  homeclick(){
    this.homebtn.image = HomeClick
    this.$router.push('/')
  },
  homeout(){
    this.homebtn.image = HomeRestore
  },
  createhover(){
    this.addleaguebtn.image = CreateHover
  },
  createclick(){
    this.addleaguebtn.image = CreateClick
    this.$router.push('/add')
  },
  createout(){
    this.addleaguebtn.image = CreateRestore
  },
  matchhover(){
    this.addmatchbtn.image = MatchHover
  },
  matchclick(){
    this.addmatchbtn.image = MatchClick
    this.$router.push('/play')
  },
  matchout(){
    this.addmatchbtn.image = MatchRestore
  },
  managehover(){
    this.manageleaguebtn.image = ManageHover
  },
  manageclick(){
    this.manageleaguebtn.image = ManageClick
    this.$router.push('/manage-league')
  },
  manageout(){
    this.manageleaguebtn.image = ManageRestore
  },
  logouthover(){
    this.logoutbtn.image = LogoutHover
  },
  logoutclick(){
    this.logoutbtn.image = LogoutClick
    this.$router.push('/logout')
    this.logoutout()
  },
  logoutout(){
    this.logoutbtn.image = LogoutRestore
  },
  addhover(){
    this.addcoursebtn.image = AddHover
  },
  addclick(){
    this.addcoursebtn.image = AddClick
    this.$router.push('/add-course')
  },
  addout(){
    this.addcoursebtn.image = AddRestore
  },
},
created(){
},
events:{

}
}
</script>
<style>
body{
  /* background: URL("./img/login.jpg") no-repeat center fixed; */
  /* background: URL("./img/unsplash.jpg") no-repeat center fixed; */
  /* background: URL("./img/option2.jpg") no-repeat center fixed; */
   background: URL("./img/option10.jpg") no-repeat center fixed;

  background-size: cover;
  font-family: sans-serif;
  color: #005229;
}
#app.logged-in{
  position: absolute;
  top: 0px;
  left: 0px;
  display: grid;
  grid-template-columns: 100vw;
  grid-template-rows: 10vh 90vh;
  grid-template-areas: "nav"
  "page";
}
.mainBody{
  grid-area: page;
  display: flex;
  justify-content: stretch;
  align-items: stretch;
}
.messenger{

}
#router-view{
  min-width: 75vw;
  width: 100vw;
}

#nav{
 grid-area: nav;
 position:static;
 background-color: #2D007A;
 background-image: url("/img/Banner.png");
 background-size: 100%;
 font-size: 14pt;
 text-align: center;
 font-family: Arial, Helvetica, sans-serif;
 width: 1fr;
 padding-top: 20px;
 padding-bottom: 20px;
 display: flex;
 justify-content: space-around;
 align-items: center;
 object-fit: fill
 /* 
 justify-content: stretch;
 align-items: center; */
}
#nav img {
  height: 10vh;
  object-fit: fill;
}
a{
  color: #ffb81f; 
}
</style>
