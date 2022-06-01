import axios from 'axios';

export default {
    viewStandings(user){
        return axios.get('/futurepath1', user);
    },
    viewrounds(user){
        return axios.get('/rounds', user);
    }
}