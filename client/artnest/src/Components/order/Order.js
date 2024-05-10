import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { createOrder } from '../../redux/product/actions';
import logo from '../../assets/artnest_logo.png';
import useRazorpay from 'react-razorpay';
function loadScript(src) {
  return new Promise((resolve) => {
    const script = document.createElement("script");
    script.src = src;
    script.onload = () => {
      resolve(true);
    };
    script.onerror = () => {
      resolve(false);
    };
    document.body.appendChild(script);
  });
}

function Order() {
  const amt = useSelector((state) => state.product.totalPrice);
  const dispatch = useDispatch();
  const orderStatus = useSelector((state) => state.product.orderStatus);
  

  

  const handlePayment = async () => {
    const res = await loadScript(
      "https://checkout.razorpay.com/v1/checkout.js"
    );

    if (!res) {
      alert("Razorpay SDK failed to load. Are you online?");
      return;
    }

    dispatch(createOrder(amt));
    const {order_id} = orderStatus;

    const options = {
      key: "rzp_test_4iFGoALGJA03xz",
      currency: "INR",
      amount: amt*100,
      order_id: order_id,
      name: "Shopping",
      description: "Enjoy The Products",
      image: {logo},
      handler: function (response) {
        // alert(response.razorpay_payment_id);
        // alert(response.razorpay_order_id);
        // alert(response.razorpay_signature);

        alert("Transaction successful");
      },
      prefill: {
        name: "",
        email: "",
        phone_number: "",
      },
    };
    const paymentObject = new window.Razorpay(options);
    paymentObject.open();
    
  };

  return (
    <div>
      Order
      <button onClick={handlePayment}>Pay ₹{amt}</button>
    </div>
  );
}

export default Order;
