<template>
  <div class="home">
    <h1>Home</h1>
    <p>You must be authenticated to see this</p>
    <google-map></google-map>
  </div>
  
</template>


<script>
import GoogleMap from "../components/GoogleMap.vue"

export default {
  name: "home",
  components: {
    GoogleMap
  }
};
</script>
