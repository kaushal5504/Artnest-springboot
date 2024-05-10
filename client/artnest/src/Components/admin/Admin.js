import React from 'react'
import './admin.css'
import { autocompleteClasses, backdropClasses } from '@mui/material'
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import logo from "../../assets/artnest_logo.png";
import HomeIcon from '@mui/icons-material/Home';
import ColorLensIcon from '@mui/icons-material/ColorLens';
import PersonIcon from '@mui/icons-material/Person';
import InventoryIcon from '@mui/icons-material/Inventory';
import Divider from '@mui/material/Divider';
import admin_pic from '../../assets/admin_pic.jpg'
import SidebarLeft from './SidebarLeft';
import SidebarRight from './SidebarRight';


function Admin() {
  return (
    <>
    <div className='admin'>
      <SidebarLeft/>
      
      <div className='mainContent'>

      </div>

      <SidebarRight/>
      
      
    </div>
    </>
    
  )
}

export default Admin