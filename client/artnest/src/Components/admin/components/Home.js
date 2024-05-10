import React from 'react'
import SidebarLeft from '../SidebarLeft'
import './home.css'
import SideBarRight from '../SidebarRight'

function Home() {
  return (
    <div className='home'>
       
        <SidebarLeft/>
        
        <div className='mainContent'>
            Home
         </div>

         <SideBarRight/>

    </div>
  )
}

export default Home