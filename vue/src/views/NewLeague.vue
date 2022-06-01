<template>
  <div>
      <form id="League-Create">
          <h1>Register League</h1>
      <div>
      <label for="league-name">League name: </label>
      <input
        type="text"
        id="league-name"
        placeholder="The Extraordinary"
        v-model="League.Name"
        required
      />
      </div>
      <label for="course">Course</label>
      <!-- Integrated course selector goes here -->
      <button type="submit">Register League</button>
      </form>

  </div>
</template>

<script>


export default {
    data(){
        return{
            League:{
                Nmae: '',
                Organizer: this.$store.state.user,
                Course: '',
                Players:[this.$store.state.user]
            }
        }
    }

}
</script>

<style>
#League-Create{
    margin: 5px;
    padding-left: 5px;
    color: #005229;
    display: flex;
    flex-direction: column;
}
</style>