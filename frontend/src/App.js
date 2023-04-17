import './App.css';

import React from "react";
import {
  BrowserRouter as Router,
  Routes,
  Route,
} from "react-router-dom";
import Navbar from './components/Navbar';
import Products from './components/Products';
import Product from './components/Product';
import Cart from './components/Cart';
import Home from './components/Home';
import { Provider } from 'react-redux';
import store from './store/coreStore';


function App() {

  return (
    <Provider store={store}>
    <div className="App">
      <Router>
      <Navbar/>
      <Routes>
        <Route path="/" element={<Home/>} />
        <Route path="/products/:categoryId" element={<Products/>}></Route>
        <Route path="/product/:productId" element={<Product/>}></Route>
        <Route path="/cart" element={<Cart/>}></Route>

      </Routes>
      </Router>
    </div>
    </Provider>
  );
}

export default App;
