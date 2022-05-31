<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>&nbsp;|&nbsp;
      <button id="toggle dark" v-on:click.prevent="toggleDark()">Toggle Dark Mode</button>
    </div>
    
    <router-view />
  </div>
</template>
<script>
export default {
methods:{
  toggleDark() {
    if(document.querySelector('html').classList.contains('dark')){
      document.querySelector('html').classList.remove('dark');
    } else {
      document.querySelector('html').classList.add('dark');
    }
  }
}  
}
</script>
<style>
/* temporarily disabled to merge
html.dark{
  background-color: rgba(15, 0, 64, 64);
  color: #FFAF00;
}
html{
  background-color: rgba(240, 255, 255, 64);
  color: #6A0012;
} */
</style>