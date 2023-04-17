import axios from 'axios'

const API_URL = 'http://localhost:8080/product/'

class ProductDataService {

    retrieveProduct(id) {
        return axios.get(`${API_URL}${id}` );
    }

}

export default new ProductDataService()