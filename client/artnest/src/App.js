import logo from './logo.svg';
import './App.css'

import Login from './Components/login/Login'
import useSelector from 'react-redux'
import {  Route,BrowserRouter,Routes } from "react-router-dom";
import Contact from './Components/contact/Contact';
import NoPage from './Components/nopage/NoPage';
import ProtectedAccess from './Components/ProtectedAccess';
import Admin from './Components/admin/Admin';
import AbstractArt from './Components/categories/AbstractArt';
import AdminHome from './Components/admin/components/Home';
import Products from './Components/admin/components/Products';
import Users from './Components/admin/components/Users';
import Home from './Components/mainPage/Home';
import Navbar from './Components/navbar/Navbar';
import AddProducts from './Components/admin/components/AddProducts';
import EditProduct from './Components/admin/components/EditProduct';
import LandscapeArt from './Components/categories/LandscapeArt';
import Wishlist from './Components/wishlist/Wishlist';
import Order from './Components/order/Order';


function App() {
  
  return (
    <div className="App">
      <BrowserRouter>

      <Login /> 
      <Routes>
      <Route path="/" element={<Home/>}/>
      <Route path="/abstractArt" element={<AbstractArt/>}/>
      <Route path="/landscapeart" element={<LandscapeArt/>}/>
      <Route path="/contact" element={<Contact/>}/>
      <Route path="/admin" element={< Admin/>}/>
      <Route path="/admin/home" element={<AdminHome/>}/>
    <Route path="/admin/products" element={<Products/>}/>
    <Route path="/admin/users" element={<Users/>}/>
    <Route path="/admin/addProduct" element={<AddProducts/>}/>
    <Route path="/admin/editProduct" element={<EditProduct/>}/>
    <Route path="/user/wishlist" element={<Wishlist/>}/>
    <Route path="/user/order" element={<Order/>}/>
      </Routes>
        
      </BrowserRouter>

     
      
    </div>
  );
}

export default App;
