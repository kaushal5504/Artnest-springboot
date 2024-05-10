
import * as actionTypes from './actionType'
import axios from 'axios'
import Cookies from 'js-cookie';


const BaseURL = "http://localhost:8080/api/auth"

export const openLoginDialog=()=>{
    console.log("action is dispatched")
    return{
       type : actionTypes.OPEN_LOGIN_DIALOG
    }
}

export const userAccess=()=>{
  return{
    type: actionTypes.USER_ACCESS
  }
}

export const closeLoginDialog=()=>{
    return{
        type : actionTypes.CLOSE_LOGIN_DIALOG
    }
}

export const addUser=(user)=>{
    return {
        type : actionTypes.ADD_USER,
        payload : user
    }
}

export const storeTokenToLocalStorage=(data)=>{
  Cookies.set('authToken', data, {
    expires: 7, // Set expiration in days (adjust as needed)
    secure: true, // Only send over HTTPS
    sameSite: 'strict' // Mitigate cross-site scripting risks
  });
  return{
    type : actionTypes.STORE_TOKEN,
    payload : data
  }
}



export function isExist(data){
    return async (dispatch)=>{
        const f = false;
        try{
            const f = await axios.post(`${BaseURL}/isExist`, data);
        }catch(err){

        }
        return f;

    };
}

export function handleSignupApi(data) {
    console.log("handleapi")
    return async (dispatch) => {
      const result = {};
      try {
        const result = await axios.post(`${BaseURL}/signup`, data);
        if (result) {
          dispatch({
            type: "ADD_USER",
            payload: result,
          });
        } else {
          dispatch({
            type: "SIGNIN_ERROR",
          });
        }
      } catch (err) {
        console.log(err)
        dispatch({
          type: "SIGNIN_ERROR",
        });
      }
      return result;
    };
  }


export function handleSigninApi(login){
  
  const { email, password } = login;
  console.log(email, password)
  return async(dispatch)=>{
    try{
      const result = await axios.post(`${BaseURL}/signin`, login);
      const { data: { token, refreshtoken } } = result;
      console.log(token+" "+refreshtoken)
      //dispatch(storeTokenToLocalStorage(token))
      
      if(token){
        Cookies.set('active_user', email, { secure: true, sameSite: 'strict' });
        dispatch({
          type : "ACTIVE_USER",
          payload : email
        })
        Cookies.set('authToken', token, {
          expires: 7, // Set expiration in days (adjust as needed)
          secure: true, // Only send over HTTPS
          sameSite: 'strict' // Mitigate cross-site scripting risks
        });
        dispatch(getUserId(email))
        console.log("token" ,Cookies.get('authToken'))
      }else{
        console.log("no token received")
      }
      
      
      
    }
  catch(err){
    console.log(err);
  }
}

}

export function checkUserAccess(token){
  const result=0;
  console.log("checkUserAccess" +token)
  return async(dispatch)=>{
    try{
       result = await axios.get(`http://localhost:8080/api/user`,{
        headers: {Authorization: `Bearer ${token}`}
      })
      console.log("result of access: ",result.status)

    }
  catch(err){
    console.log(err);
  }
  return result.status;
}

}

export function getUserId(email){

    
  return async(dispatch)=>{
    try{
      const res = await axios.get(`http://localhost:8080/api/all/getUserId/${email}`)
      console.log("userId ",res.data);
      dispatch({
        type : "USER_ID",
        payload : res.data
      })
      
    }
  catch(err){
    console.log(err);
  }
}

}


 

