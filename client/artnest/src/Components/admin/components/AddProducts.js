import React, { useState } from 'react'
import SidebarLeft from '../SidebarLeft'
import SidebarRight from '../SidebarRight'
import "./addproducts.css"
import Divider from "@mui/material/Divider";
import Radio from '@mui/material/Radio';
import RadioGroup from '@mui/material/RadioGroup';
import FormControlLabel from '@mui/material/FormControlLabel';
import FormControl from '@mui/material/FormControl';
import FormLabel from '@mui/material/FormLabel';
import { useDispatch } from 'react-redux';
import { addProduct } from '../../../redux/product/actions';
import { Button } from '@mui/material';


const product={
    title:"",
    price:"",
    quantity:"",
    description:"",
    category:"",
    
    
}
function  AddProducts () {
    const dispatch = useDispatch();
    const [newProduct, setnewProduct] = useState(product)
    const [selectedRole, setSelectedRole] = useState("");
    const [file, setfile] = useState()
    const handleRoleChange = (event) => {
      setSelectedRole(event.target.value);
    };
    

    const handlesubmit = async () => {
      if (!file) {
        console.error("Please select a file to upload.");
        return; // Prevent unnecessary API call if no file is selected
      }
  
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
  
        
        const imageUrl = data.url;
        if (imageUrl) {
         
          const updatedProduct = { ...newProduct, filePath: imageUrl };
          const resp = dispatch(addProduct(updatedProduct));
          console.log(resp)
         
          
        } else {
          console.warn("Uploaded image URL not found in response data.");
        }
      } catch (err) {
        console.error("Error uploading file:", err);
      }
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
        <div className="add-heading">Add Products</div>
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
        



        <div class="formbold-input-file">
          <div class="formbold-filename-wrapper">
            
              Attach Painting Photo<br/>
              <input type="file" name="file" 
               onChange={handleFile} id="upload"/>
          </div>
        </div>

        <button onClick={handlesubmit} class="formbold-btn"  >
            Add 
        </button>
    
  </div>
</div>
        
      </div>
      <SidebarRight/>
    </div>
  )
}

export default AddProducts
