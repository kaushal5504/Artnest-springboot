import React from "react";

import "./abstarctart.css";
import Card from "../cards/Card";
import logo from "../../assets/artnest_logo.png";
import Divider from '@mui/material/Divider';
import { useSelector } from "react-redux";
import { useEffect , useState} from "react";
import { useDispatch } from "react-redux";
import { getAllProducts, getByCategory, getProductByTitle, getProducts } from "../../redux/product/actions";
import { Link } from "react-router-dom";
import axios from 'axios'
import { checkingLike, updateDislikes, updateLikes } from "../../redux/product/actions";
import { getUserId } from "../../redux/user/actions";
import Cookies from "js-cookie";
//import { getProducts } from "../../redux/product/actions";


function LandscapeArt() {
  const dispatch = useDispatch();
  const products = useSelector((state)=>state.product.categoryProducts);
  const active_user =Cookies.get("active_user")
  const user_id = useSelector((state)=>state.user.user_id)
  

  useEffect(() => {
    dispatch(getUserId(active_user));
    const res = dispatch(getByCategory("Landscape Art"))
   
    console.log("user_id in land",user_id)
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
          <text className="dancing-script-artnest "><Link className=".link" to="/" >ArtNest</Link></text>
        
      </div>
      <Divider/>
      <div className="quote">
         <h3>Landscape Art</h3>
        <br/>
        <p>"Standing in the field, facing the mighty landscape, all the sadness and the conflicts of the soul fade away, and a feeling of peace comes over me."</p>
        <Divider/>

      </div>
      <div className="card-grid">
        {products.map(card_object)}   
      </div>

     
    </>
  );
}

export default LandscapeArt;
