import axios from 'axios';

export default {
    viewLeagues(user){
        return axios.get('/leagues', user);
    },
    viewStandings(user, leagueID){
        return axios.get(`/leagues/${leagueID}/scores`, user);
    },
    viewRounds(user){
        return axios.get('/rounds', user);
    },
    addRound(user, round){
        return axios.post('/rounds/addround', round.score, round.roundDate, user, round.golfCourse)
    },
<<<<<<< HEAD
    addLeague(user, name, golfCourse){
        return axios.post('/leagues/addleague', name, golfCourse, user)
=======
    addLeague(league){
        return axios.post('/leagues/addleague', league)
>>>>>>> 9c2c2d85e69c2e0c0d2ecc1cc11b37a6b76734b7
    },
    manageLeague(user, roster, leagueId){
        return axios.put(`/leagues/${leagueId})`, user, roster)
    },
    reportRound(user, round, score){
        return axios.put(`/rounds/${round}`, score, user)
    },


}