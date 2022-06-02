import axios from 'axios';

export default {
    viewStandings(user){
        return axios.get('/futurepath1', user);
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
        return axios.put(`/futurepath2/${leagueId})`, user, roster)
    },
    reportRound(user, round, score){
        return axios.put(`/rounds/${round}`, score, user)
    },


}