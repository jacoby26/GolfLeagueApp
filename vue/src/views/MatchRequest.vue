<template>
<div class="normalBox">
  <Map />
</div>
</template>

<script>
import Map from "../components/GoogleMap.vue"
export default {
components:{
Map
},
}
</script>

<style>
.normalBox{
  margin: 5px;
  background-color: rgba(186,199,202,.9);
  padding: 5px;
}
</style>