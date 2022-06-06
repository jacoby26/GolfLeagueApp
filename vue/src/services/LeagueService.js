import axios from 'axios';

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
    addRound(round, leagueId){
        return axios.post(`/leagues/${leagueId}/addround`, round, this.$store.league)
    },
    addLeague(league, user){
        return axios.post('/leagues/addleague', league, user)
    },
    manageLeague(user, roster, leagueId){
        return axios.put(`/leagues/${leagueId}`, user, roster)
    },
    reportRound(user, round, score){
        return axios.put(`/rounds/${round}`, score, user)
    },
    

}