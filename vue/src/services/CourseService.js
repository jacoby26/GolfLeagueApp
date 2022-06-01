import axios from 'axios'

const http = axios.create({
    baseURL: 'http://localhost:8080'
})

class GolfCourseService
{
    getAllCourses() {
        return http.get('/courses')
    }
}

export default new GolfCourseService()