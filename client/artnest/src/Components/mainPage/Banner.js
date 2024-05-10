import React from 'react'
import Crousel from 'react-multi-carousel';
import "react-multi-carousel/lib/styles.css";
import c1 from "../../../src/assets/c1.png"
import c2 from "../../assets/c2.png"
import c3 from "../../assets/c3.png"

const responsive = {
  
  desktop: {
    breakpoint: { max: 3000, min: 1024 },
    items: 1,
  },
  tablet: {
    breakpoint: { max: 1024, min: 464 },
    items: 1,
  },
  mobile: {
    breakpoint: { max: 464, min: 0 },
    items: 1,
  },
};


function Banner() {

  return (
    <div >
     <Crousel
        
         responsive={responsive}
         swipeable={false}
         draggable={false}
         showDots={false}
         infinite={true}
        autoPlay={true}
         autoPlaySpeed={3000}
         containerClass="carousel-container"
         dotListClass="custom-dot-list-style"
        itemClass="carousel-item-padding-40-px"
        
      >
        <img src={c1} alt=''/>
        <img src={c2} alt=''/>
        <img src={c3} alt=''/>
      </Crousel>
    
    </div>
  )
}

export default Banner