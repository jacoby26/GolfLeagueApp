import axios from 'axios';

export default {
    viewLeagues(user){
        return axios.get('/leagues/', user);
    },
    viewStandings(leagueID){
        return axios.get(`/leagues/${leagueID}/scores`);
    },
    viewRounds(user){
        return axios.get('/rounds', user);
    },
    addRound(user, round){
        return axios.post('/rounds/addround', round.score, round.roundDate, user, round.golfCourse)
    },
    addLeague(league){
        return axios.post('/leagues/addleague', league)
    },
    manageLeague(user, roster, leagueId){
        return axios.put(`/leagues/${leagueId})`, user, roster)
    },
    reportRound(user, round, score){
        return axios.put(`/rounds/${round}`, score, user)
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

    }


}