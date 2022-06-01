<template>
<div class="login-body">
  <div class="app-title">
    <h1 id="title"> <img id="app-logo" src="..\img\logoedit.png"/> BACK NINE </h1>
  </div>
  <div id="login" class="text-center">
    <div class="login-header">
      <h1 class="h3-mb-3-font-weight-normal">Please Sign In</h1>
    </div>
    <form class="form-signin" @submit.prevent="login">
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
      <label for="username" class="sr-only">Username</label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder=""
        v-model="user.username"
        required
        autofocus
      />
      <label for="password" class="sr-only">Password</label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder=""
        v-model="user.password"
        required
      />
      <button id="sign-in" type="submit">Sign in</button>
      <router-link :to="{ name: 'register' }">Need an account?</router-link>
    </form>
  </div>
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
/* potential color palette
old gold #eeea03 
brown #5D5038
white
*/
img#app-logo {
  position: relative;
  height: 75px;
}
body {
  background: URL("../img/login.jpg") no-repeat center fixed;
  background-size: cover;
  font-family: sans-serif;
  
}
/* div#nav {  
  display: none;
} */
.app-title {
  display: flex;
  justify-content: center;
  color: white;
  text-align: center;
  position: relative;
  font-size: 32px;
  margin: 0;
  padding: 100px 0px 75px 0px;
  width: 100%; 
  text-shadow: 0 0 10px black;
}
.login-body {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100%;
}
div#login {
    box-shadow: 2px 4px 10px;
    border-radius: 7px 7px 5px 5px;
    background: white;
}
.login-header {
  color: white;
  border-radius: 5px 5px 0 0;
  text-transform: uppercase;
  background: #5D5038; 
  width: 100%;
}
h1.h3-mb-3-font-weight-normal {
  margin: 0px;
  padding: 20px 100px;
  text-shadow: 0 0 1px #5D5038;
}
.sr-only {
  text-align: center;
  padding: 25px 0 5px;
  font-size: 18px;
  font-weight: 500;
  text-transform: uppercase;
}
a {
  margin: 15px auto;
  text-align: center;
  max-width: auto;
  color: #5D5038;
}
#sign-in {
  border-radius: 5px 5px;
  border: #5D5038;
  cursor: pointer;
  margin: 35px auto 20px;
  padding: 10px;
  max-width: 100px;
  text-transform: uppercase;
  background: #5D5038;
  color: white;
}
input {
  padding: 8px;
}

</style>
