<template>
<div class="normalBox" id="main-grid">
 <h1 id="about-us-title">Meet the Development Team</h1>
 
</div>
</template>

<script>

export default {

}
</script>

<style>
.normalBox{
  margin: 5px;
  background-color: rgba(186,199,202,.9);
  border-radius: 5px;
  padding: 5px;
}

#main-grid {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  grid-template-areas: 
  "title"
  "Jacob Nate Dillon"
  "Alex  Niko";
  row-gap: 20px;
  column-gap: 50px;
  align-items: center;
}

#about-us-title {
  grid-area: title;
  justify-self: center;
}


</style>