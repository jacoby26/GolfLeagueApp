import axios from 'axios'

const http = axios.create({
    baseURL: 'http://localhost:8080'
})

class GolfCourseService
{
    getAllCourses() {
        return http.get('/courses')
    }

    addCourse(course){
        return http.post('/add-course', course)
    }
    checkcourse(courseID){
        return http.get(`/courses/${courseID}`)
    }
}

export default new GolfCourseService()