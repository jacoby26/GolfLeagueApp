<template>
  <div id="register" class="text-center">
    <form class="form-register" @submit.prevent="register">
      <h1 class="h3 mb-3 font-weight-normal">Create Account</h1>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <div id ="register-form">
      <label id="userlabel" for="username" class="sr-only">Username</label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <label id="passlabel" for="password" class="sr-only">Password</label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <input
        type="password"
        id="confirmPassword"
        class="form-control"
        placeholder="Confirm Password"
        v-model="user.confirmPassword"
        required
      />
      <router-link :to="{ name: 'login' }" id="login">Have an account?</router-link>
      <button id="submit" class="btn btn-lg btn-primary btn-block" type="submit">
        Create Account
      </button>
      </div>
    </form>
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style>
.form-register{
  display: flex;
  flex-direction: column;
  align-items: center ;
}
#register-form{
  display: grid;
  grid-template-columns: 50% 50%;
  text-align: right;
  grid-template-areas:
  "userprompt userfield"
  "passprompt passfield"
  "passprompt confirmPassword"
  "login submit";
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
#login{
  grid-area: login;
  margin-right: 5px;
  min-width: 60px;
}
#submit{
  grid-area: submit;
  width: 75%;
  min-width: 60px;
}
#confirmPassword{
  grid-area: confirmPassword;
  margin-bottom: 1px;
}
</style>
