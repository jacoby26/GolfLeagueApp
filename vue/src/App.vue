<template>
  <span id="app">
    <span id ="style-toggle">
      <a v-on:click.prevent ="toggleDark">{{DisplayModeColor}}</a>
    </span>
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'home' }">Create League</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'home' }">Manage League</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
    <router-view />
  </span>
</template>
<script>
export default {
  data() {
    return{
      DisplayModeColor : ' \u263D',
    }
  },
methods:{
  toggleDark() {
    if(document.querySelector('html').classList.contains('dark')){
      document.querySelector('html').classList.remove('dark');
      this.DisplayModeColor = "\u263D"
    } else {
      document.querySelector('html').classList.add('dark');
      this.DisplayModeColor = '\u2600'
    }
  }
}  
}
</script>
<style>
#style-toggle{
  position: absolute;
  left:1%;
  font-size: 24pt;
  width: 1.5%;
  text-align: right;
  top: 1%;
}
html.dark{
  background-color: #0C003F;
  color: #FFAF00;
}
html{
  background-color: rgba(245, 255, 250, 100);
  color: #6A0012;
  text-align: center;
}
.dark a{
  color: #FFAF00;
}
a{
  color: #6A0012; 
}
</style>
