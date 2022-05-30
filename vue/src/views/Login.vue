<template>
  <div id="login" class="text-center">
    <form class="form-signin" @submit.prevent="login">
      <h1 class="h3 mb-3 font-weight-normal">Please Sign In</h1>
      <div
        class="alert alert-danger"
        role="alert"
        v-if="invalidCredentials"
      >Invalid username and password!</div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >Thank you for registering, please sign in.</div>
      <div id ="login-form">
      <label id = "userlabel" for="username" class="sr-only">Username</label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <label id = "passlabel" for="password" class="sr-only">Password</label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <router-link :to="{ name: 'register' }" id="go-get-new-account">Need an account?</router-link>
      <button id="submit-login" type="submit">Sign in</button>
      </div>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>
<style>
.form-signin{
  display: flex;
  flex-direction: column;
  align-items: center ;
}
#login-form{
  display: grid;
  grid-template-columns: 50% 50%;
  text-align: right;
  grid-template-areas:
  "userprompt userfield"
  "passprompt passfield"
  "register submit";
}
#userlabel{
  grid-area: userprompt;
  margin-right: 5px;
  margin-bottom: 1px;
}
#passlabel{
  grid-area: passprompt;
  margin-right: 5px;
  margin-bottom: 1px;
}
#username{
  grid-area: userfield;
  margin-bottom: 1px;
}
#password{
  grid-area: passfield;
  margin-bottom: 1px;
}
#go-get-new-account{
  grid-area: register;
  margin-right: 5px;
}
#submit-login{
  grid-area: submit;
  width: 33%;
  min-width: 60px;
}
</style>