import React from "react";
import { Box, Button } from "@mui/material";
import logo from "../../assets/artnest_logo.png";
import search from "../../assets/search_icon_n.png";
import FavoriteBorderOutlinedIcon from "@mui/icons-material/FavoriteBorderOutlined";
import ShoppingCartIcon from "@mui/icons-material/ShoppingCart";
import SearchIcon from "@mui/icons-material/Search";
import { useState, useEffect } from "react";
import AppBar from "@mui/material/AppBar";
import { useSelector, useDispatch } from "react-redux";
import { activeUser, openLoginDialog } from "../../redux/user/actions";
import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import Banner from "./Banner";
import Carousel from "./Carousel";
import "./home.css"
import selftaught from '../../assets/self-taught.jpeg'
import passion from '../../assets/passion.jpg'
import i1 from '../../assets/Abstract art.jpeg'
import i2 from '../../assets/admin_pic.jpg'
import i3 from '../../assets/artnest_logo.png'
import i5  from '../../assets/ColorfulGirl.png'
import i6 from '../../assets/Diwali.png'
import i7 from '../../assets/Eagle.png'
import Cookies from 'js-cookie';



function Home() {
  const [open, setopen] = useState(false);
  const dispatch = useDispatch();
  const active_user_cookie = Cookies.get("active_user");
  const active_user = useSelector((state)=>state.user.active_user)
  const token = useSelector((state) => state.user.token);
  const navigate = useNavigate();
  
  const handleLogout = () => {
    Cookies.remove('authToken');
    Cookies.remove('active_user')
    dispatch({
      type:"ACTIVE_USER",
      payload:""
    })
    navigate("/");
  };
  useEffect(() => {
   
    console.log("active_user cookie" + active_user);
  },[]);

  const openDialog = () => {
    setopen(true);
  };
  return (
    <>
    <div className="home-navbar">
        <div className="logo_block">
          <div className="img">
            <img src={logo} alt="artnest_logo" />
            <text className="dancing-script-artnest ">ArtNest</text>
          </div>
        </div>
        <div className="searchBar_block">
          <input type="text" placeholder="Search.." />

          <div className="category">
            <ul >
              <li><Link to="/abstractArt" className="link" >Abstract Art</Link></li>
              <li><Link to="/landscapeart" className="link" >Landscape Art</Link></li>
              <li><Link to="/abstractArt" className="link" >Traditional Art</Link></li>
              <li>Modern Art</li>
              <li>Dirty Art</li>
              <li>Comtempory Art</li>
            </ul>
          </div>
        </div>
        <div className="other_block">
          <div className="combined">
            {active_user||active_user_cookie ? (
              <button className="login-button" onClick={() => handleLogout()}>
                LogOut
              </button>
              
            ) : (
              <button
                className="login-button"
                onClick={() => dispatch(openLoginDialog())}
              >
                Login
              </button>
            )}

            <text>/</text>
            <button className="signup-button">Signup</button>
          </div>

          <div className="heart">
          <Link to="/user/wishlist"><FavoriteBorderOutlinedIcon style={{ background: "white" }} /></Link>
            
          </div>
          <div className="cart">
            <ShoppingCartIcon style={{ background: "white" }} />
          </div>
          <Link to="/admin">admin</Link>
          
        </div>
      </div>
   
      <div className="carousel">
       <Banner/> 
      </div>
      <div className="divider">
        <h1>Gift To Your Love Ones</h1>

      </div>
      <div className="grids">
        <div className="first-row">
          <div className="first-text">
          "Formal education can only take you so far. The artist must find a way to keep learning, to keep the creative wellspring flowing. For me, self-discovery became an ongoing process."
          </div>
          <div className="first-photo">
            <img src={selftaught} alt=""/>
          </div>
        </div>
        <div className="divider-two">
         <p>engage your self and go on doing painting and have a joy</p>

      </div>
        <div className="second-row">
        <div className="second-text">
        <img src={passion} alt=""/>
          </div>
          <div className="second-photo">
          "Art washes away from the soul the dust of everyday life.Nobody sees a flower the way I see a flower. I see its mystery, its beauty and its struggle to exist against all odds"

          </div>
         
        </div>
        
      </div>
      <div className="divider-two">
         <p>recommended paintings</p>

      </div>
      <div class="grid">
  <div class="box blue wide">
    <img src={passion} alt=""/>
  </div>
  <div class="box green square">
  <img src={i1} alt=""/>
  </div>
  <div class="box green square">
  <img src={passion} alt=""/>
  </div>
  <div class="box green square">
  <img src={i2} alt=""/>
  </div>
  <div class="box green tall">
  <img src={passion} alt=""/>
  </div>
  <div class="box red square">
  <img src={i3} alt=""/>
  </div>
  <div class="box green square">
  <img src={passion} alt=""/>
  </div>
  <div class="box red wide">
  <img src="" alt=""/>
  </div>
  <div class="box blue square">
  <img src={passion} alt=""/>
  </div>
  <div class="box blue tall">
  <img src={i5} alt=""/>
  </div>
  <div class="box red wide">
  <img src={passion} alt=""/>
  </div>
  <div class="box green wide">
  <img src={i6} alt=""/>
  </div>
  <div class="box red square">
  <img src={passion} alt=""/>
  </div>
  <div class="box red wide">
  <img src={i2} alt=""/>
  </div>
  <div class="box green square">
  <img src={passion} alt=""/>
  </div>
  <div class="box green square">
  <img src={i1} alt=""/>
  </div>
  <div class="box green square">
  <img src={passion} alt=""/>
  </div>
</div>
      <div className="home-footer">
        
      </div>
     
    </>
    
      
   
  );
}

export default Home;
