<template>
  <div id="app" v-bind:class="{'logged-in': logged}">
    <div id="nav" v-if="$store.state.token != ''">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'add-league' }">Create League</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'home' }">Manage League</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
    <router-view />
    <Messenger v-if="$store.state.token != ''"/>
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
Messenger{
  grid-area: messaging;
}
router-view{
  grid-area: router;
}

#nav{
 grid-area: nav;
 position:static;
 background-image: url("../Banner.png");
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
html{
  background-color: #BAC7CA;
  color: #ffb81f;
}
a{
  color: #ffb81f; 
}
</style>
