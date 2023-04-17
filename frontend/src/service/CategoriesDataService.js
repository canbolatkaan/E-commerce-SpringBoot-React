import axios from 'axios'

const API_URL = 'http://localhost:8080/categories/'

class CategoriesDataService {

    retrieveAllCategories() {
        return axios.get(API_URL);
    }

    createCategories(course) {
        return axios.post(API_URL, course);
    }
}

export default new CategoriesDataService()