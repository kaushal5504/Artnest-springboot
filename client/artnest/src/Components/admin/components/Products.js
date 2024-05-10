import React from "react";
import SidebarLeft from "../SidebarLeft";
import SidebarRight from "../SidebarRight";
import "./products.css";
import Divider from "@mui/material/Divider";
import photo from "../../../assets/ColorfulGirl.png";
import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { deleteProduct, getProducts, updateProductQuantity } from "../../../redux/product/actions";
import { Link } from "react-router-dom";
import {
  Button,
  ButtonGroup,
  CloseButton,
  Grid,
  GridItem,
  Image,
  Text,
  useToast,
} from "@chakra-ui/react";
import { useNavigate } from 'react-router-dom';

function Products() {
  const products = useSelector((state) => state.product.products);
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const [qty, setQty] = useState(0);
  useEffect(() => {
    
    dispatch(getProducts());
  }, [products]);

  let title = "";

  const handleIncQty = (q,title) => {
    title = title
    setQty(q + 1);
  };

  const handleDecQty = (q,title) => {
    title = title
    setQty(q - 1);
  };

  useEffect(() => {
    dispatch(updateProductQuantity(title, qty));
  }, [qty]);

  const handledelete = (p) => {
    const {id} = p;
    const { title } = p;
    console.log(id);
    dispatch(deleteProduct(id));
    dispatch(getProducts())
  };

  const handleEdit=(product)=>{
    console.log(product.category)
    navigate(`/admin/editProduct`,{ state: { product } });
  }

  

  

  const onQuantityChange = (e) => {
    console.log("new: ", e);
  };
  return (
    <div className="products">
      <SidebarLeft />

      <div className="mainContent">
        <div className="heading">Products</div>
        <Divider />
        <div className="productsRow">
          <table>
            <tbody>
              {products.length > 0 &&
                products.map((product) => (
                  <tr key={product.id}>
                    <td>
                      <img src={product.filePath} alt="" />
                    </td>
                    <td>{product.title}</td>
                    <td>
                      <button onClick={()=>handleEdit(product)} >Edit</button>
                     
                    </td>
                    <td>
                    <ButtonGroup
          display="flex"
          flexDirection={{
            base: "column",
            sm: "row",
          }}
          alignItems="center"
          gap="5px"
        >
          <Button
            disabled={product.quantity <= 1}
            colorScheme="teal"
            variant="solid"
            onClick={()=>handleDecQty(product.quantity, product.title)}
            size="sm"
          >
            -
          </Button>
          <Button color={"black"} variant="solid" size="sm">
            {product.quantity}
          </Button>
          <Button
            disabled={product.quantity > 9}
            colorScheme="teal"
            variant="solid"
            size="sm"
            onClick={()=>handleIncQty(product.quantity,product.title)}
          >
            +
          </Button>
        </ButtonGroup>
                    </td>
            
                    <td>
                      <button onClick={() => handledelete(product)}>
                        Delete
                      </button>
                    </td>
                  </tr>
                ))}
              {products.length === 0 && ( // Show message if no products
                <tr>
                  <td colSpan="4">No products found.</td>
                </tr>
              )}
            </tbody>
          </table>
        </div>
        <div className="addProduct">
          <Link to="/admin/addProduct" className="add-link">
            Add Product
          </Link>
        </div>
      </div>

      <SidebarRight />
    </div>
  );
}

export default Products;
