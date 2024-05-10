import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import "./wishlist.css"
import {
  deleteFromWishlist,
  getAllWishlistProducts,
  getTotalPrice,
} from "../../redux/product/actions";
import Cookies from "js-cookie";
import logo from "../../assets/artnest_logo.png"
import "../admin/components/products.css";
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
import { useNavigate } from "react-router-dom";
import Divider from '@mui/material/Divider';
import { Link } from "react-router-dom";
function Wishlist() {
  const dispatch = useDispatch();
  const products = useSelector((state) => state.product.wishlistProducts);
  const user = Cookies.get("active_user");
  const total_price = useSelector((state) => state.product.totalPrice);

  useEffect(() => {
    dispatch(getAllWishlistProducts(user));
    dispatch(getTotalPrice(user))
    
  }, [products,total_price]);

  const navigate = useNavigate();
  const [qty, setQty] = useState(0);

  let title = "";

  //   const handleIncQty = (q,title) => {
  //     title = title
  //     setQty(q + 1);
  //   };

  //   const handleDecQty = (q,title) => {
  //     title = title
  //     setQty(q - 1);
  //   };

  //   useEffect(() => {
  //     dispatch(updateProductQuantity(title, qty));
  //   }, [qty]);

  const handledelete = (p) => {
    const { id } = p;
    const { title } = p;
    const dataToSend = {
      product_id: p.id,
      email: user,
    };
    console.log(id);
    dispatch(deleteFromWishlist(dataToSend));
    dispatch(getAllWishlistProducts());
  };

  const handleEdit = (product) => {
    console.log(product.category);
    navigate(`/admin/editProduct`, { state: { product } });
  };

  const onQuantityChange = (e) => {
    console.log("new: ", e);
  };

  return (
    <>
       <div className="abstract-logo_block">
        
        <img src={logo} alt="artnest_logo" />
        <text className="dancing-script-artnest "><Link className=".link" to="/">ArtNest</Link></text>
      
    </div>
    <Divider/>
    <div className="wishlist-heading">
       WishList
    </div>
    <Divider/>
      <div className="wishlist-productsRow">
        <table>
          <tbody>
            {products.length > 0 &&
              products.map((product) => (
                <tr key={product.id}>
                  <td>
                    <img src={product.filePath} alt="" />
                  </td>
                  <td>{product.title}</td>
                  <td>{product.price}</td>
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
                        // onClick={()=>handleDecQty(product.quantity, product.title)}
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
                        // onClick={()=>handleIncQty(product.quantity,product.title)}
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
      <div className="totalPrice">
       Total Price : {total_price}

      </div>
      <div className="ordernow-btn">
        <button ><Link to="/user/order">ordernow</Link>  </button>
      </div>
    </>
  );
}

export default Wishlist;
