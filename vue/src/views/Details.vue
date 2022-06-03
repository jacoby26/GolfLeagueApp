<template>
<div>
<Leaderboard v-bind:element="element" v-if="item.isleague"/>
<Round v-bind:element="element" v-if="item.isround"/>
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
props:["item", "element"],
}
</script>

<style>

</style>