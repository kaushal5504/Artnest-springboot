import React, { useState } from 'react'
import SidebarLeft from '../SidebarLeft'
import SidebarRight from '../SidebarRight'
import "./addproducts.css"
import "./editProduct.css"
import Divider from "@mui/material/Divider";
import Radio from '@mui/material/Radio';
import RadioGroup from '@mui/material/RadioGroup';
import FormControlLabel from '@mui/material/FormControlLabel';
import FormControl from '@mui/material/FormControl';
import FormLabel from '@mui/material/FormLabel';
import { useDispatch } from 'react-redux';
import { addProduct, editProduct } from '../../../redux/product/actions';
import { Button } from '@mui/material';

import { useEffect } from 'react';
import { useLocation } from 'react-router-dom';

const productn={
  id:"",
    title:"",
    price:"",
    quantity:"",
    description:"",
    category:"",
    filePath:""
    
    
}
function EditProduct () {
    const dispatch = useDispatch();
    const [newProduct, setnewProduct] = useState(productn)
    const [selectedRole, setSelectedRole] = useState("");
    const [file, setfile] = useState()
    const location = useLocation();
    const product = location.state?.product; 
    

    useEffect(() => {
      console.log(product)
     setnewProduct({
      id :product.id,
      title:product.title,
    price:product.price,
    quantity:product.quantity,
    description:product.description,
    category:product.category.name,
    filePath:product.filePath
     })
    }, [])
    
    
    
    const handleRoleChange = (event) => {
      setSelectedRole(event.target.value);
    };

    const handlesubmit = async () => {
      console.log(product)
      let imageUrl;
      if (!file) {
        imageUrl=product.filePath
        
      }else{
        const formData = new FormData();
      formData.append('file', file);
      formData.append("upload_preset", "artnest");
      formData.append("cloud_name", "dppkkbval");
  
      try {
        const response = await fetch("https://api.cloudinary.com/v1_1/dppkkbval/image/upload", {
          method: "post",
          body: formData,
        });
  
       
        const data = await response.json();
        console.log(data.url); 
  
        
        imageUrl = data.url;
        
      } catch (err) {
        console.error("Error uploading file:", err);
      }

      }
      const updatedProduct = { ...newProduct, filePath: imageUrl };
      dispatch(editProduct(updatedProduct));
  
      
    };
  const onInputChange = (e) => {
    console.log(newProduct);
    setnewProduct({ ...newProduct, [e.target.name]: e.target.value });
    
  };

  const handleFile=(e)=>{
    setfile(e.target.files[0])
    

    
  }
  

  

  return (
    <div className='addProducts'>
      <SidebarLeft/>
      <div className='mainContent'>
        <div className="add-heading">Edit Product</div>
        <Divider />

        <div class="formbold-main-wrapper">
  
  <div class="formbold-form-wrapper">
   
        <div class="formbold-input-flex">
          <div>
              <input
              type="text"
              name="title"
              id="title"
              placeholder="Title"
              class="formbold-form-input"
              value={newProduct.title}
              onChange={(e)=>onInputChange(e)}
              />
              <label for="Title" class="formbold-form-label">Title</label>
          </div>
          <div>
              <input
              type="text"
              name="category"
              id="category"
              placeholder="Category"
              class="formbold-form-input"
              value={newProduct.category}
              onChange={(e)=>onInputChange(e)}
              />
              <label for="Title" class="formbold-form-label">Category</label>
          </div>
        </div>

        <div class="formbold-input-flex">
          <div>
              <input
              type="number"
              name="price"
              id="price"
              placeholder="Price"
              class="formbold-form-input"
              value={newProduct.price}
              onChange={(e)=>onInputChange(e)}
              />
              <label for="email" class="formbold-form-label"> Price </label>
          </div>
          <div>
              <input
              type="number"
              name="quantity"
              id="quantity"
              placeholder="Quantity"
              value={newProduct.quantity}
              onChange={(e)=>onInputChange(e)}
              class="formbold-form-input"
              />
              <label for="phone" class="formbold-form-label">Quantity </label>
          </div>
        </div>

        <div class="formbold-textarea">
            <textarea
                rows="6"
                name="description"
                id="description"
                placeholder="Write description..."
                value={newProduct.description}
                onChange={(e)=>onInputChange(e)}
                class="formbold-form-input"
            ></textarea>
            <label for="message" class="formbold-form-label"> Description </label>
        </div>
        <div className='notFile'>
        {!file?(
            

            <img src={product.filePath} alt=''/>
            
          ):
          <></>}
        </div>


        <div class="formbold-input-file">
        

          <div class="formbold-filename-wrapper">
          
              Want To Attach Other Photo<br/>
              <input type="file" name="file" 
               onChange={handleFile} id="upload"/>
          </div>
          
        </div>

        <button onClick={handlesubmit} class="formbold-btn"  >
            Edit
        </button>
    
  </div>
</div>
        
      </div>
      <SidebarRight/>
    </div>
  )
}

export default EditProduct
