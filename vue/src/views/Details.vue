<template>
<div>
<Leaderboard v-if="isleague"/>
<Round v-if="isround"/>
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
  isleague(){
    return this.item === 'league';
  },
  isround(){
    return this.item === 'game';
  }
}
}

</script>

<style>

</style>