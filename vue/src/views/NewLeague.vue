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
    margin-top: 2%;
    margin-left: 30%;
    margin-right: 30%;
    padding-left: 5px;
    background-color: rgba(186,199,202,.9);
    border-radius: 5px;
    display: flex;
    flex-direction: column;
    justify-content: space-evenly;
    align-items: center;
    
}
#League-Create > div:nth-child(1) > form > div:nth-child(4){
    padding-left: 23%;
    padding-right: 20%;
}
#League-Create > div:nth-child(1) > form > h1 {
    padding-left: 7%;
    padding-right: 5%;
}
#intro {
    padding-left: 30%;
}
#searchForm {
    padding-left: 27.5%;
}
#League-Create > div:nth-child(2){
    
}

</style>
