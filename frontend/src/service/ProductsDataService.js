import axios from 'axios'

const API_URL = 'http://localhost:8080/products/'

class ProductsDataService {

    retrieveAllProducts() {
        return axios.get(`${API_URL}` );
    }

}

export default new ProductsDataService()