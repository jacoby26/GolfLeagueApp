import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if(currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    locations: [],
    leagues:[],
    currentLeague: {},
    invites:[],
    currentInvite: {},
    non_member_golfers: [],
    member_golfers: [],
    rounds: [],
    currentRound: {},
    forecast:{}
  },
  mutations: {
    LOAD_COURSES(state, locations){
      state.locations = locations;
    },
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user',JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    },
    LOAD_LEAGUE(state, league){
      state.leagues.push(league);
    },
    FETCH_LEADERBOARD(state, leagueId){
      state.currentLeague = state.leagues.find((entry)=>{
        return entry.leagueID === leagueId
      })
      
    },
    EMPTY_LEAGUES(state){
      state.leagues = [];
      state.currentLeague = {};
      state.invites = [];
      state.currentInvite = {};
      state.non_member_golfers = [];
      state.member_golfers = [];
    },
    LOAD_INVITES(state, invite){
      state.invites.push(invite);
    },
    REMOVE_INVITE(state){
      state.invites = state.invites.filter(invite => {
        return invite != state.currentInvite;
      });
      state.currentInvite = {};
    },
    VIEW_GOLFERS(state, members, non_members){
      state.member_golfers = members;
      state.non_member_golfers = non_members;
    },
    LOAD_ROUNDS(state, round){
      state.rounds.push(round);
    },
    EMPTY_ROUNDS(state){
    state.rounds = [];
    state.currentRound = {};
    },
    FETCH_ROUND(state, teeTimeID){
      state.currentRound = state.rounds.find((entry)=>{
        return entry.teeTimeID === teeTimeID;
      });
    },
    SET_WEATHER(state, weather){
      state.forecast = weather;
    }
  }
})
