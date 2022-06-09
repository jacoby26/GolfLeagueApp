<template>
  <div id="register-box">
    <div class="app-title">
      <img id="app-logo" src="..\img\logoedit.png"/>
    <h1 id="title">BACK NINE</h1>
  </div>
    <div id="register" class="text-center">
      <div class="create-header">
          <h1 class="h3-mb-3-font-weight-normal">Create Account</h1>
       </div>
      <form class="form-register" @submit.prevent="register">
        <div class="alert-alert-danger" role="alert" v-if="registrationErrors">
          {{ registrationErrorMsg }}
       </div>
       <label for="username" class="sr-only">Username</label>
       <input
          type="text"
          id="username"
          class="form-control"
          placeholder="Username"
          v-model="user.username"
          required
          autofocus
       />
       <label for="password" class="sr-only">Password</label>
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
        <button id="create-acc" class="btn btn-lg btn-primary btn-block" type="submit">
          Create Account
        </button>
        <router-link :to="{ name: 'login' }" id="go-to-login">Have an account?</router-link> 
      </form>
    </div>
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
#register-box {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100%;
}
#register-box form {
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: flex-start;
  margin: 0px 50px 25px;
  
}
#register {
    margin-left: 37vw;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    width: 25vw;
    box-shadow: 2px 4px 10px;
    border-radius: 7px 7px 5px 5px;
    background: white;
    color: black;
    position: relative;
    margin: auto auto;
}
.create-header {
  color: white;
  border-radius: 5px 5px 0 0;
  text-transform: uppercase;
  background: #2D007A; 
  width: 100%;
  text-align: center;
}
#create-header h1.h3-mb-3-font-weight-normal {
  margin: 0px;
  padding: 20px 100px;
  text-shadow: 0 0 1px #2D007A;
}
.sr-only {
  text-align: center;
  padding: 25px 0 5px;
  font-size: 18px;
  font-weight: 500;
  text-transform: uppercase;
}

#create-acc {
  border-radius: 5px 5px;
  border: #005229;
  cursor: pointer;
  margin: 35px auto 20px;
  padding: 10px;
  max-width: 100px;
  text-transform: uppercase;
  background: #005229;
  color: white;
}
#register input {
  padding: 8px;
}
#confirmPassword {
  margin: 10px 0 0 0;
}
.form-register {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
#go-to-login {
  margin: 15px auto;
  text-align: center;
  max-width: auto;
  color: #2D007A;
}
.alert-alert-danger {
  margin: 10px 0 0 0;
  font-weight: 700;
  
}
</style>
