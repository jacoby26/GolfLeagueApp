<template>
  <div>
      <h1>Message Invite or Remove players</h1>
      <h2>Select players to invite:</h2>
      <button @click="logCurrentLeague"></button>
  </div>
</template>

<script>
export default {
  methods: {
    logCurrentLeague() {
      console.log(this.$store.state.currentLeague)
    }
  }
}
</script>

<style>

</style>