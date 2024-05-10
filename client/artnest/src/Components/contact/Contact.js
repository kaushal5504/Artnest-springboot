import React from 'react'
import { useSelector, useDispatch } from "react-redux";
import { useEffect } from 'react';
import { checkUserAccess } from '../../redux/user/actions';
import { useNavigate } from "react-router-dom";

function Contact() {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const user_access = useSelector((state)=>(state.user.user_access))
  useEffect(() => {
    const token = localStorage.getItem("token")
    const status = (checkUserAccess(token))
    console.log("status :"+ status);
    if(!user_access){
      alert("Accessible to user")
      navigate("/");
    }
    
    
  }, [])
  
  return (
    <div >Contact</div>
  )
}

export default Contact