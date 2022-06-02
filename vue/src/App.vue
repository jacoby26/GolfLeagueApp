<template>
  <div id="app" v-bind:class="{'logged-in': logged}">
    <div id="nav" v-if="logged">
      <img id="homebtn" v-bind:src='homebtn.image' @mouseover="homehover()" @mouseout="homeout()" v-on:click="homeclick()"/>
      <img id="addleaguebtn" v-bind:src='addleaguebtn.image' @mouseover="addhover()" @mouseout="addout()" v-on:click="addclick()"/>
      <img id="addmatchbtn" v-bind:src='addmatchbtn.image' @mouseover="matchhover()" @mouseout="matchout()" v-on:click="matchclick()"/>
      <img id="manageleaguebtn"  v-bind:src='manageleaguebtn.image' @mouseover="managehover()" @mouseout="manageout()" v-on:click="manageclick()"/>
      <img id="addcoursebtn"  @mouseover="managehover()" @mouseout="manageout()" v-on:click="manageclick()"/>
      <router-link :to="{ name: 'add-course' }" >Add New Course</router-link>
      <img id="logoutbtn" v-bind:src='logoutbtn.image' @mouseover="logouthover()" @mouseout="logoutout()" v-on:click="logoutclick()" v-if="$store.state.token != ''"/>
    </div>
    <router-view id="router-view" />
    <Messenger id="messenger" v-if="$store.state.token != ''"/>
  </div>
</template>
<script>
import Messenger from "./components/Messenger.vue";
import HomeRestore from "./img/HomeRestore.png";
import HomeHover from "./img/HOH.png";
import HomeClick from "./img/HOC.png";
import AddRestore from "./img/CLR.png";
import AddHover from "./img/CLH.png";
import AddClick from "./img/CLC.png";
import MatchRestore from "./img/RMR.png";
import MatchHover from "./img/RMH.png";
import MatchClick from "./img/RMC.png";
import ManageRestore from "./img/MLR.png";
import ManageHover from "./img/MLH.png";
import ManageClick from "./img/MLC.png";
import LogoutRestore from "./img/LOR.png";
import LogoutHover from "./img/LOH.png";
import LogoutClick from "./img/LOC.png";

export default {
  data() {
    return{
      homebtn:{image:HomeRestore},
      addleaguebtn:{image:AddRestore},
      addmatchbtn:{image:MatchRestore},
      manageleaguebtn:{image:ManageRestore},
      logoutbtn:{image:LogoutRestore},
    }
  },
  computed:{
    logged(){
      return this.$store.state.token != '';
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
  addhover(){
    this.addleaguebtn.image = AddHover
  },
  addclick(){
    this.addleaguebtn.image = AddClick
    this.$router.push('/add')
  },
  addout(){
    this.addleaguebtn.image = AddRestore
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
  },
  logoutout(){
    this.logoutbtn.image = LogoutRestore
  },
}  
}
</script>
<style>
body{
  background-image: URL("./img/login.jpg");
  background-size: cover;
  font-family: sans-serif;
  color: #005229;
}
#app.logged-in{
  position: absolute;
  top: 0px;
  left: 0px;
  display: grid;
  grid-template-columns: 75vw 25vw;
  grid-template-rows: 10vh 90vh;
  grid-template-areas: "nav nav"
  "router messaging";
}
#messenger{
  grid-area: messaging;
}
#router-view{
  grid-area: router;
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
