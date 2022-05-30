<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
    <router-view />
  </div>
</template>
<<<<<<< Updated upstream
=======
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
html.dark{
  background-color: #0C003F;
  color: #FFAF00;
}
html{
  background-color: rgba(235, 255, 245, 64);
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
>>>>>>> Stashed changes
