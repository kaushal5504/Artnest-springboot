import React from 'react'
import Divider from '@mui/material/Divider';
import admin_pic from '../../assets/admin_pic.jpg'
function SidebarRight() {
  return (
    <div className='sidebarRight'>
        <div className='sidebarRight'>
        <div className='user'>
            <img src={admin_pic} alt='user_pic'/>
            <div className='ADMIN'>
              <p>ADMIN</p>
            </div>
        </div>
        <Divider/>

        <div className='recent_items'>

        </div>

      </div>
    </div>
  )
}

export default SidebarRight