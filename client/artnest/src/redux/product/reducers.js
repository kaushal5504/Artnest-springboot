import Wishlist from '../../Components/wishlist/Wishlist';
import * as actionTypes from './actionType'
const initialState = {
    products: [],
    categoryProducts:[],
    wishlistProducts:[],
    totalPrice:"",
    orderStatus:[]
  };
  
  const productReducer = (state = initialState, action) => {
    switch (action.type) {

      case actionTypes.GET_PRODUCTS:
        
        return{
          ...state,
          products: action.payload
        }

        case actionTypes.GET_PRODUCTS_BY_CATEGORY:
   
        return{
          ...state,
          categoryProducts: action.payload
        }

        case actionTypes.GET_WISHLIST_PRODUCTS:
 
        return{
          ...state,
          wishlistProducts: action.payload
        }

        case actionTypes.GET_Total_PRICE:

        return{
          ...state,
          totalPrice: action.payload
        }
        case actionTypes.ORDER_STATUS:

        return{
          ...state,
          orderStatus: action.payload
        }
  
      default:
        return state; // Return unchanged state for unknown actions
    }
  };
  
  export default productReducer;