import React, { useState, useEffect } from 'react';

const Carousel = ({ slides, interval = 5000 }) => {
  const [currentIndex, setCurrentIndex] = useState(0);
  const length = slides.length;

  const handleNext = () => {
    const newIndex = (currentIndex + 1) % length;
    setCurrentIndex(newIndex);
  };

  const handlePrevious = () => {
    const newIndex = (currentIndex - 1 + length) % length; // Handle underflow
    setCurrentIndex(newIndex);
  };

  useEffect(() => {
    const timeout = setTimeout(() => {
      handleNext();
    }, interval);

    return () => clearTimeout(timeout);
  }, [currentIndex, interval, length, handleNext]);

  return (
    <div className="carousel">
      <div className="carousel-container">
        {slides.map((slide, index) => (
          <div
            key={index}
            className={`carousel-item ${currentIndex === index ? 'active' : ''}`}
          >
            {slide}
          </div>
        ))}
      </div>
      <button className="carousel-button carousel-button-prev" onClick={handlePrevious}>
        Previous
      </button>
      <button className="carousel-button carousel-button-next" onClick={handleNext}>
        Next
      </button>
    </div>
  );
};

export default Carousel;
