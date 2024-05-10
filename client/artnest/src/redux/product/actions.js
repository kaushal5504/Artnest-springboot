
import axios from 'axios'
import Cookies from 'js-cookie';
//import Razorpay from 'razorpay'
import { FIND_PRODUCT, GET_PRODUCTS } from './actionType'

axios.interceptors.request.use(
  (config) => {
    if (config.data instanceof FormData) {
      // Override content type only for multipart form data requests
      config.headers['Content-Type'] = 'multipart/form-data';
    }
    return config;
  },
  (error) => {
    // Handle errors
    return Promise.reject(error);
  }
);


export function getProductByTitle(title){
  
    return async(dispatch)=>{
      try{
         const {data} = await axios.get(`http://localhost:8080/api/product/findProductByTitle/${title}`)
        console.log("result of access: ",data)
  
      }
    catch(err){
      console.log(err);
    }
    
  }
  
  }

  export function getProducts(){

    
    return async(dispatch)=>{
      try{
        const response = await axios.get("http://localhost:8080/api/all/getAllProducts")
        
        dispatch({
          type : "GET_PRODUCTS",
          payload : response.data

        } 
       

        )
      }
    catch(err){
      console.log(err);
    }
  }
  
  }


  export function addProduct(product){

    console.log("get products:", product)
    const token = Cookies.get('authToken');
    return async(dispatch)=>{
      try{
        const response = await axios.post("http://localhost:8080/api/admin/createProduct",product,{

          headers: {Authorization: `Bearer ${token}`}
        })
        console.log("result of access: ",response.data)
        return response.data.statusCode;
      }
    catch(err){
      console.log(err);
    }
    
  }
  
  }
  //////////////////////////////////////////////////////////////////////////
  export function updateLikes(updatedLike){
    console.log(updatedLike)
    const token = Cookies.get('authToken');
    console.log(token)
    const {product_id,email} =updatedLike;
    console.log(product_id,email)
    
    return async(dispatch)=>{
      try{
        const response = await axios.post("http://localhost:8080/api/user/update-likes",updatedLike,{
          headers: {Authorization: `Bearer ${token}`,
        "Content-Type":"application/json"}
        })
        console.log("result of access: ",response.data)
        return response.data.statusCode;
      }
    catch(err){
      console.log(err);
    }
    
  }
  
  }
  ///////////////////////////////////////////////////////////////////////////
  export function updateDislikes(updatedDislike){
    
    const token = Cookies.get('authToken');
    console.log(token)
    
    
    return async(dispatch)=>{
      try{
        const response = await axios.post("http://localhost:8080/api/user/update-dislikes",updatedDislike,{
          headers: {Authorization: `Bearer ${token}`,
        "Content-Type":"application/json"}
        })
        console.log("result of access: ",response.data)
        return response.data.statusCode;
      }
    catch(err){
      console.log(err);
    }
    
  }
  
  }
  ////////////////////////////////////////////////////////////
  export function checkingLike(checkLike){
    
    const token = Cookies.get('authToken');
    console.log(token)
    const {product_id,email} =checkLike;
    console.log(product_id,email)
    
    
    return async(dispatch)=>{
      try{
        const response = await axios.post("http://localhost:8080/api/user/isLiked",checkLike,{
          headers: {Authorization: `Bearer ${token}`,
        "Content-Type":"application/json"}
        })
      
        return response.data;
      }
    catch(err){
      console.log(err);
    }
    
  }
  
  }

  export function editProduct(product){

 
    const token = Cookies.get('authToken');
    return async(dispatch)=>{
      try{
        const response = await axios.put(`http://localhost:8080/api/admin/updateProduct/${product.id}`,product,{

          headers: {Authorization: `Bearer ${token}`}
        })
     
      }
    catch(err){
      console.log(err);
    }
  }
  
  }

  export function deleteProduct(id){

    const token = Cookies.get('authToken');
    return async(dispatch)=>{
      try{
        const response = await axios.delete(`http://localhost:8080/api/admin/deleteProduct/${id}`,{

          headers: {Authorization: `Bearer ${token}`}
        })
      
      }
    catch(err){
      console.log(err);
    }
  }
  
  }

  export function getByCategory(category){

    
    return async(dispatch)=>{
      try{
        const res = await axios.get(`http://localhost:8080/api/all/getByCategory/${category}`)
      
        dispatch({
          type : "GET_PRODUCTS_BY_CATEGORY",
          payload : res.data

        } )
        
        
      }
    catch(err){
      console.log(err);
    }
  }
  
  }

  



//   export function addProduct(product){
//     console.log("dispatcheditemp: ", product)
    
//     const formData = new FormData();
//      formData.append('file', file); // Append file directly


// const productDetailsJson = JSON.stringify(product);

// //formData.append('productDetails', productDetailsJson);
// formData.append('productDetails',
// new Blob([JSON.stringify(productDetailsJson)], { 
//   type: 'application/json'
// }));
//     const token = localStorage.getItem('token')
//     console.log("form data: ",formData.get('productDetails'))

//     const headers = {
//       'Authorization': `Bearer ${token}`,
//       //'Content-Type': 'multipart/form-data; boundary=<calculated when request is sent>' 
//       //'Content-Type':`multipart/form-data; boundary=${generateUniqueBoundaryString()}`
//       mode: 'no-cors'
//     };
//     return async(dispatch)=>{
//       try {
//         const response = await fetch('http://localhost:8080/api/admin/createProduct', {
//           method: 'POST',
         
//           headers,
//           body: formData
//         });
    
//         if (!response.ok) {
//           throw new Error(`HTTP error! status: ${response.status}`);
//         }
    
//         const data = await response.json(); // Parse JSON response
//         console.log("doneeee");
//         return data; // Return the parsed data for further processing
//       } catch (err) {
//         console.error('Error adding product:', err);
//         // Handle errors appropriately, e.g., dispatch an error action in Redux
//       }
//   }
  
//   }

export const updateProductQuantity = (title, qty) => async (dispatch) => {
  const token = Cookies.get('authToken');
  try {
    
    const response = await axios.put(`http://localhost:8080/api/admin/updateProductQuantity/${title}`,
      {quantity:qty,
        headers: {Authorization: `Bearer ${token}`,
        mode: 'no-cors'}
      }
    )
    
     
  } catch (error) {
    console.log(error)
  }
};

export function addToWishlist(data){

  const token = Cookies.get('authToken');
  
  const {product_id,email} =data;
 
  
  return async(dispatch)=>{
    try{
      const response = await axios.post("http://localhost:8080/api/user/addToWishlist",data,{
        headers: {Authorization: `Bearer ${token}`,
      "Content-Type":"application/json"}
      })
     
      return response.data.statusCode;
    }
  catch(err){
    console.log(err);
  }
  
}

}

export function deleteFromWishlist(data){
    
  const token = Cookies.get('authToken');

  
  
  return async(dispatch)=>{
    try{
      const response = await axios.post("http://localhost:8080/api/user/deleteFromWishlist",data,{
        headers: {Authorization: `Bearer ${token}`,
      "Content-Type":"application/json"}
      })
    
      return response.data.statusCode;
    }
  catch(err){
    console.log(err);
  }
  
}
}

export function getAllWishlistProducts(user){
    
  const token = Cookies.get('authToken');
  
  
  
  return async(dispatch)=>{
    try{
      const response = await axios.get(`http://localhost:8080/api/user/getAllWishlistProducts/${user}`,{
        headers: {Authorization: `Bearer ${token}`,
      "Content-Type":"application/json"}
      })
     
      dispatch({
        type:"GET_WISHLIST_PRODUCTS",
        payload: response.data
      })
      return response.data.statusCode;
    }
  catch(err){
    console.log(err);
  }
  
}

}

export function getTotalPrice(user){
    
  const token = Cookies.get('authToken');
 
  
  
  return async(dispatch)=>{
    try{
      const response = await axios.get(`http://localhost:8080/api/user/getTotalPrice`,{
        headers: {Authorization: `Bearer ${token}`,
      "Content-Type":"application/json"}
      })
     
      dispatch({
        type:"GET_Total_PRICE",
        payload: response.data
      })
      return response.data.statusCode;
    }
  catch(err){
    console.log(err);
  }
  
}

}
export function createOrder(amt){
    
  const token = Cookies.get('authToken');
 
  
  
  return async(dispatch)=>{
    try{
      const response = await axios.post(
        `http://localhost:8080/api/user/createOrder`,
        {amt:amt},
        {
          headers: {Authorization: `Bearer ${token}`, 
          "Content-Type": 'application/json'},
        }
      );
      dispatch({
        type:"ORDER_STATUS",
        payload:response.data
      })
     
     
    }
  catch(err){
    console.log(err);
  }
  
}

}

