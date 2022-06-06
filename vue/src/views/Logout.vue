<template>
  <h1>Logout</h1>
</template>

<script>
export default {
  created() {
    this.$store.commit("LOGOUT");
    this.$store.commit('EMPTY_LEAGUES');
    this.$router.push("/login");
  }
};
</script>
