<template>
  <div id="app" v-bind:class="{'logged-in': logged}">
    <div id="nav" v-if="logged">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'add-league' }">Create League</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'add-match' }">Request Game</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'home' }">Manage League</router-link>&nbsp;|&nbsp;

      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
    <router-view id="router-view" />
    <Messenger id="messenger" v-if="$store.state.token != ''"/>
  </div>
</template>
<script>
import Messenger from "./components/Messenger.vue";
export default {
  data() {
    return{
    }
  },
  computed:{
    logged(){
      return this.$store.state.token != '';
    }
  },
  components:{
    Messenger
  },
methods:{
}  
}
</script>
<style>
body{
  background-image: URL("./img/login.jpg");
  background-size: cover;
  font-family: sans-serif;
  color: #ffb81f;
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
}
a{
  color: #ffb81f; 
}
</style>
