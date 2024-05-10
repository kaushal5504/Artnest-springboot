import React from "react";
import "./login.css";
import Dialog from "@mui/material/Dialog";
import { useState, useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import {
  activeUser,
  addUser,
  closeLoginDialog,
  handleApi,
  handleSigninApi,
  handleSignupApi,
  isExist,
} from "../../redux/user/actions";
import { Box, Typography, TextField, Button } from "@mui/material";
import Radio from '@mui/material/Radio';
import RadioGroup from '@mui/material/RadioGroup';
import FormControlLabel from '@mui/material/FormControlLabel';
import FormControl from '@mui/material/FormControl';
import FormLabel from '@mui/material/FormLabel';
import axios from 'axios';

const signupInitialState = {
  name: "",
  email: "",
  phoneNumber: "",
  password: "",
 
};

const loginInitialState = {
  email: "",
  password: "",
};

function Login() {
  const dispatch = useDispatch();
  const loginStatus = useSelector((state) => state.user.isOpen);

  const newUser = useSelector((state) => state.user.newUser);
  const token = useSelector((state)=> state.user.token)

  
  const [toggle, settoggle] = useState(true);
  const [signup, setsignup] = useState(signupInitialState);
  const [login, setlogin] = useState(loginInitialState);

  useEffect(() => {
    console.log("newUser:", newUser);
    // Log newUser after state updates
  }, [newUser]);


  const handleClose = () => {
    dispatch(closeLoginDialog()); // Dispatch action to close dialog
  };

  const handleToggle = () => {
    settoggle(!toggle);
    setsignup(signupInitialState);
    console.log("sign" + signup.email);
    setlogin(loginInitialState);
    console.log("login" + login.emailL);
  };

  const onInputChange = (e) => {
    setsignup({ ...signup, [e.target.name]: e.target.value });
    console.log(signup);
  };

  const onloginInputChange = (e) => {
    console.log("login: l",e.target.value)
    setlogin({ ...login, [e.target.name]: e.target.value });
  };

const [selectedRole, setSelectedRole] = useState("USER"); // Initial selected role

const handleRoleChange = (event) => {
  setSelectedRole(event.target.value);
};

  const handleSignup = () => {
    console.log("signupp",signup);
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    const phoneRegex = /^\d+$/;
    const validationErrors = [];

    if (!emailRegex.test(signup.email)) {
      validationErrors.push("Please enter a valid email address.");
    }

    if (
      !phoneRegex.test(signup.phoneNumber) ||
      signup.phoneNumber.length > 10
    ) {
      validationErrors.push(
        "Phone number should only contain digits (max 10 characters)."
      );
    }

    // if(dispatch(isExist(signup.email))){
    //   validationErrors.push("Email already exits")
    // }

    if (validationErrors.length > 0) {
      alert(validationErrors.join("\n")); // Combine errors into a single message
      return; // Prevent further processing if validation fails
    }

    const dataToSend = { // Create a new object to hold signup data
      ...signup, // Spread the signup state for existing fields
      role: selectedRole, // Add the selectedRole state
    };
    
    dispatch(handleSignupApi(dataToSend));
    //dispatch(handleSignupApi(dataToSend));
    handleToggle();
  };

  const isValidEmail = (email) => {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
  };

  const handleLogin=async()=>{
    
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    const validationErrors = [];
    // if (!emailRegex.test(login.email)) { 
    //   validationErrors.push("Please enter a valid email address.");
    // }

    // if(!isExist(login)){
    //   validationErrors.push("No such user exist");
    // }

    if (validationErrors.length > 0) {
      alert(validationErrors.join("\n")); // Combine errors into a single message
      return; // Prevent further processing if validation fails
    }
    console.log("login object before dispatch:", login);
  
    dispatch(handleSigninApi(login)); 
     
    
    
    dispatch(closeLoginDialog()); 
    

  }

  

  return (
    <Dialog open={loginStatus} onClose={handleClose}>
      {toggle ? (
        <Box className="container">
          <Box className="login">Login</Box>
          <Box className="inputs">
            <input

              id="login-first"
              label="Enter Email"
              variant="outlined"
              name="email"
              onChange={(e) => onloginInputChange(e)}
              value={login.email}
            />
            <input
              id="login-second"
              label="Enter Password"
              variant="outlined"
              name="password"
              onChange={(e) => onloginInputChange(e)}
              value={login.password}
            />
          </Box>
          <Button id="login-button" variant="contained" onClick={handleLogin}>
            Login
          </Button>
          <Box className="for-signup">
            <Typography>Don't have an account</Typography>
            <Button id="signup-button" variant="text" onClick={handleToggle}>
              Signup
            </Button>
          </Box>
        </Box>
      ) : (
        <Box className="container">
        <Box className="login">Signup</Box>
           <RadioGroup
        row
        aria-labelledby="demo-row-radio-buttons-group-label"
        name="role"
  value={selectedRole} // Set the value prop with the state
  onChange={handleRoleChange}
      >
        <FormControlLabel  value="USER" control={<Radio />} label="User"  />
        <FormControlLabel  value="ADMIN" control={<Radio />} label="Admin"  />
       
       
      </RadioGroup>  
          <Box className="inputs-signup">
            <input
              
              id="first"
              label="Enter Name"
              variant="outlined"
              name="name"
              onChange={(e) => onInputChange(e)}
              value={signup.name}
            />
            <input
              id="second"
              label="Enter email"
              variant="outlined"
              name="email"
              onChange={(e) => onInputChange(e)}
              value={signup.email}
            />
            <input
              id="third"
              label="Enter phone number"
              variant="outlined"
              name="phoneNumber"
              onChange={(e) => onInputChange(e)}
              value={signup.phoneNumber}
            />
            <input
              id="fourth"
              label="Enter Password"
              variant="outlined"
              name="password"
              onChange={(e) => onInputChange(e)}
              value={signup.password}
            />
          </Box>
          <button 
            id="signup-button2"
            variant="contained"
            onClick={handleSignup}
          >
            signup
          </button>
        </Box>
      )}
    </Dialog>
  );
}

export default Login;
