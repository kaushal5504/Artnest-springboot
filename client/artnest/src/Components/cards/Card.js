import React,{useState} from "react";
import "./card.css";
import GradeIcon from '@mui/icons-material/Grade';
import ChatBubbleIcon from '@mui/icons-material/ChatBubble';
import FavoriteBorderOutlinedIcon from '@mui/icons-material/FavoriteBorderOutlined';
import FavoriteOutlinedIcon from '@mui/icons-material/FavoriteOutlined';
import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { addToWishlist, checkingLike,  updateDislikes, updateLikes } from "../../redux/product/actions";
import { getUserId } from "../../redux/user/actions";
import Cookies from "js-cookie";
const dataforlikeanddislike = {
  email: "",
  product_id: "",
};
function Card(props) {
  const [isLiked, setIsLiked] = useState(props.liked); 
  const active_user =Cookies.get("active_user")
  const product_id = props.id;
  const user_id = useSelector((state)=>state.user.user_id)
  let specificProduct
  
  const dispatch = useDispatch();
  const [data, setdata] = useState(dataforlikeanddislike);

  useEffect(() => {
   console.log("active_user_card    ",active_user);
    
    
   
  }, [])

  // useEffect(() => {
  //   dispatch(getUserId(active_user));
  //   console.log("user_id",user_id)
  //   const usersLiked = props.usersLiked.slice(); // Create a copy to avoid mutation (optional)

  //   console.log("users",usersLiked)
    
  //   specificProduct = usersLiked.find((product) => product === user_id);
  //  console.log("specificProduct",specificProduct)
  //   if(specificProduct===user_id){
  //     setIsLiked(true)
  //     console.log("islike in if ", isLiked)
  //  }else{
  //    setIsLiked(false)
  //    console.log("islike ", isLiked)
  //  }
   
  // }, [isLiked])
  
  // useEffect(() => {
  //   const fetchData = async () => {
  //     const dataToSend = {
  //       product_id,
  //       email: active_user,
  //     };

  //     const isProductLiked = await dispatch(checkingLike(dataToSend)); // Assuming checkingLike returns a boolean
  //     console.log("isliked",isProductLiked)

  //     setIsLiked(isProductLiked);
  //   };

  //   fetchData();
  // }, [dispatch, active_user, product_id]);
  

  const handleLikeClick = () => {
    if(isLiked===false){
      setIsLiked(true); 
      console.log("in if")
      const datatosend=
        {
        product_id:product_id,
        email:active_user
      }
      console.log(datatosend);
      
      dispatch(updateLikes(datatosend))
    }else{
      setIsLiked(false)
      console.log("in else")
      const dataToSend=
        {
    
          product_id:product_id,
          email:active_user
          
      }
      dispatch(updateDislikes(dataToSend))
      
    }
    
  };

  const handleWishlist=()=>{
    const dataToSend=
        {
    
          product_id:product_id,
          email:active_user
        }
          
    dispatch(addToWishlist(dataToSend))
  }
  return (
    <>
      <div className="card">
        <img src={props.imgsrc} alt="" className="card_img" />
        <div className="card_info">
          <div className="card_title">{props.title} </div>
          <div className="card_category">{props.category}</div>
          <div className="card_price">{props.price}</div>
          {active_user?(
            <div className="card_like_comment">
            <button className="like-btn" onClick={handleLikeClick}>
              {
                isLiked?(
                  <FavoriteOutlinedIcon
                  sx={{color: "red"}}
                  />
                ):(
                  <FavoriteBorderOutlinedIcon
                  />
                )
              }

              { props.like ? (
                <div>{props.like}</div>
              ):(
                <div></div>
              )
            }
            

            </button>
            <button onClick={handleWishlist} >wishlist</button>
             
             <ChatBubbleIcon/>
            
             

            
             

          </div>
            

          ):(
            <></>
          )}
         
          
        </div>
      </div>
    </>
  );
}

export default Card;
