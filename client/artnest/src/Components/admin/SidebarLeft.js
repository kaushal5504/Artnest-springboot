import React from 'react'
import { autocompleteClasses, backdropClasses } from '@mui/material'
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import logo from "../../assets/artnest_logo.png";
import HomeIcon from '@mui/icons-material/Home';
import ColorLensIcon from '@mui/icons-material/ColorLens';
import PersonIcon from '@mui/icons-material/Person';
import InventoryIcon from '@mui/icons-material/Inventory';
import Divider from '@mui/material/Divider';
import admin_pic from '../../assets/admin_pic.jpg'
function SidebarLeft() {
  return (
    <div className='sidebar'>
        <div className='logo'>
        <img src={logo} alt="artnest_logo" />
        </div>
        <Divider/>
        <div className='items'>
          <ul>
            <li>
            <HomeIcon/>
            <a href='/admin/home'>Home</a>
            </li>
            <li>
            <ColorLensIcon/>
            <a href='/admin/products'>Products</a>
            </li>
            <li>
              <PersonIcon/>
              <a href=''>Users</a>
            </li>
            <li>
              <InventoryIcon/>
              <a href=''>Orders</a>
            </li>
          </ul>
        </div>
        <Divider/>
        <div className='logout'>
             <div className='logout_icon'>
               <AccountCircleIcon style={{'backgroundColor':'inherit',width:'auto'}} />
             </div>
             <div className='username'>
              <p>Divyanshi</p>


             </div>
        </div>
      </div>
  )
}

export default SidebarLeft