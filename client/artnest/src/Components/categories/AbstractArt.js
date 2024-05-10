import React from "react";

import "./abstarctart.css";
import Card from "../cards/Card";
import logo from "../../assets/artnest_logo.png";
import Divider from '@mui/material/Divider';
import { useSelector } from "react-redux";
import { useEffect , useState} from "react";
import { useDispatch } from "react-redux";
import { getAllProducts, getByCategory, getProductByTitle, getProducts } from "../../redux/product/actions";
import axios from 'axios'
import { Link } from "react-router-dom";
import { getUserId } from "../../redux/user/actions";
import Cookies from "js-cookie";
//import { getProducts } from "../../redux/product/actions";


function AbstractArt() {
  const dispatch = useDispatch();
  const products = useSelector((state)=>state.product.categoryProducts);
  const active_user =Cookies.get("active_user")
  const user_id = useSelector((state)=>state.user.user_id)

  useEffect(() => {
    dispatch(getUserId(active_user));
    const res = dispatch(getByCategory("Abstract Art"))
   
    console.log("abstract",products)
  }, [])
  

  const handle = (e)=>{
    dispatch(getProducts())
    // const response = await axios.get("http://localhost:8080/api/all/getAllProducts")
    // console.log("result of access: ", response)

    console.log("products in ad", products)



  }

  function card_object(val){
    console.log("user_id",user_id)
    const usersLiked = val.usersLiked.slice(); // Create a copy to avoid mutation (optional)

    console.log("users",usersLiked)
    
   const specificProduct = usersLiked.find((product) => product === user_id);
   console.log("specificProduct",specificProduct)
   let valb;
   if(specificProduct===user_id){
    valb = true;
   }else{
    valb=false;
   }
    
    return(
      <>
      
      <Card className="card"
      liked ={valb}
      id ={val.id}
      imgsrc={val.filePath}
      title={val.title}
      category={val.category.name}
      price={val.price}
    />
      </>
     

    )
   
  }
  
  return (
    <>
      <div className="abstract-logo_block">
        
          <img src={logo} alt="artnest_logo" />
          <text className="dancing-script-artnest "><Link className=".link" to="/">ArtNest</Link></text>
        
      </div>
      <Divider/>
      <div className="quote">
         <h3>Abstract Art</h3>
        <br/>
        <p>"Let your imagination dance with the colors and shapes. This art is a gateway to your inner world."</p>
        <Divider/>

      </div>
      <div className="card-grid">
        {products.map(card_object)}   
      </div>

     
    </>
  );
}

export default AbstractArt;
