import axios from 'axios';

// let config = {
//     headers: {
//       "Content-Type": "application/json",
//       'Access-Control-Allow-Origin': '*',
//       }
//     }

export default {
    viewLeagues(user){
        return axios.get('/leagues/', user);
    },
    viewStandings(leagueID){
        return axios.get(`/leagues/${leagueID}/scores`);
    },
    viewRounds(user, leagueId){
        return axios.get(`/leagues/${leagueId}/rounds`, user, this.$store.league);
    },
    addRound(round){
        return axios.post(`/leagues/addround`, round)
    },
    addLeague(league, user){
        return axios.post('/leagues/addleague', league, user)
    },
    manageLeague(user, roster, leagueId){
        return axios.put(`/leagues/${leagueId}`, user, roster)
    },
    reportRound(score){
        return axios.post('/rounds/addround/user', score)
    },
    viewInvites(user){
        return axios.get('/invites/', user);
    },
    AddInvites(invite){
        return axios.post('/invites/', invite);
    },
    ActionInvites(invite){
        return axios.put('/invites/', invite);
    },
    getMembers(leagueID){
        return axios.get(`/leagues/${leagueID}/members`);
    },
    getNonMembers(leagueID){
        return axios.get(`/leagues/${leagueID}/Nonmembers`);
    },
    viewManagedLeagues(user){
        return axios.get('/leagues/Managed', user);
    },
    viewAllUsers(){
        return axios.get(`/users`);
    },
    viewAllRounds(user){
        return axios.get(`/rounds`, user);
    },
    viewRoundDetails(roundID){
        return axios.get(`/rounds/${roundID}`);
    },
}