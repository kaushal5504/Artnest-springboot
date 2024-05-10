import * as actionTypes from "./actionType"; // Assuming action types are defined here
import { activeUser } from "./actions";

const initialState = {
  isOpen: false,

  newUser: {
    name: "",
    email: "",
    phoneNumber: "",
    password: "",
  },

  addingUser: false,

  token:"",

  active_user:"",

  user_access: false,

  products:[],
  user_id:""
};

const userReducer = (state = initialState, action) => {
  switch (action.type) {
    case actionTypes.OPEN_LOGIN_DIALOG:
      return {
        ...state.isOpen,
        isOpen: true,
      };

    case actionTypes.CLOSE_LOGIN_DIALOG:
      return {
        ...state.isOpen,
        isOpen: false,
      };

    case actionTypes.ADD_USER:
      console.log("action of adduser is called");
      console.log(action.payload);
      return {
        ...state,
        newUser: {
          ...state.newUser,
          name: action.payload?.name || "",
          email: action.payload?.email || "",
          phoneNumber: action.payload?.phoneNumber || "",
          password: action.payload?.password || "",
        },
        

        addingUser: true,
      };

    case actionTypes.STORE_TOKEN:
      return {
        ...state.token,
        token: action.payload,
      };

      case actionTypes.ACTIVE_USER:
        console.log("reducer active user")
        return {
          ...state.active_user,
          active_user: action.payload,
        };
      case actionTypes.USER_ACCESS:
        return{
          ...state.user_access,
          user_access: true

        }
      
      case actionTypes.USER_ID:
          return{
            ...state.user_id,
            user_id: action.payload
  
          }

    default:
      return state; // Return unchanged state for unknown actions
  }
};

export default userReducer;


