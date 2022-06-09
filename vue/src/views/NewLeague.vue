<template>
  <div id="League-Create-main">
      <div id="League-Create">
          <CreateLeagueForm />
          <Map/>
      </div>    
  </div>
</template>

<script>
import Map from "../components/GoogleMap.vue";
import CreateLeagueForm from "../components/CreateLeagueForm.vue"
export default {
    name: "new-league",
    components:{
        Map,
        CreateLeagueForm
    },
    

}
</script>

<style>
#League-Create, 
#League-Create-main {
    margin: 5px;
    padding-left: 5px;
    background-color: rgba(186,199,202,.9);
    border-radius: 5px;
    display: flex;
    flex-direction: column;
}
</style>
