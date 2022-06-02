<template>
<div>
<Leaderboard v-bind:item="item" v-if="!isround"/>
<Round v-bind:item="item" v-if="isround"/>
</div>
</template>

<script>
import Leaderboard from "../components/Leaderboard.vue";
import Round from "../components/Round.vue"
export default {
components:{
  Leaderboard,
  Round
},
props:["item"],
computed:{
  isround(){
    if (this.item != null){
    return true;
    }
    return false;
  }
}
}
</script>

<style>

</style>