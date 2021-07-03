import React, { useState, useEffect } from "react";
import {
    CardElement,
    useStripe,
    useElements
} from "@stripe/react-stripe-js";
import axios from "axios";

 const CheckoutForm = (props) => {
    const [succeeded, setSucceeded] = useState(false);
    const [error, setError] = useState(null);
    const [processing, setProcessing] = useState('');
    const [disabled, setDisabled] = useState(true);
    const [clientSecret, setClientSecret] = useState('');
    const stripe = useStripe();
    const elements = useElements();

    const cardStyle = {
        style: {
            base: {
                color: "#32325d",
                fontFamily: 'Arial, sans-serif',
                fontSmoothing: "antialiased",
                fontSize: "16px",
                "::placeholder": {
                    color: "#32325d"
                }
            },
            invalid: {
                color: "#fa755a",
                iconColor: "#fa755a"
            }
        }
    };

    const handleChange = async (event) => {
        // Listen for changes in the CardElement
        // and display any errors as the customer types their card details
        setDisabled(event.empty);
        setError(event.error ? event.error.message : "");
    };

     const handleSubmit = async (event) => {
         // We don't want to let default form submission happen here,
         // which would refresh the page.
         //setLoading(true)
         event.preventDefault();
         setProcessing(true);
         if (!stripe || !elements) {
             // Stripe.js has not yet loaded.
             // Make sure to disable form submission until Stripe.js has loaded.
             return;
         }

         const result = await stripe.confirmCardPayment(props.secreteValue, {
             payment_method: {
                 card: elements.getElement(CardElement),
                 billing_details: {
                     name: props.name,
                 },
             }
         });

         if (result.error) {
             // Show error to your customer (e.g., insufficient funds)
             //redirect to payment page
             console.log(result.error.message);
         } else {
             // The payment has been processed!
             if (result.paymentIntent.status === 'succeeded') {
                 // Show a success message to your customer
                 // There's a risk of the customer closing the window before callback
                 // execution. Set up a webhook or plugin to listen for the
                 // payment_intent.succeeded event that handles any business critical
                 // post-payment actions.
                 orderConfirmationRequest(props.paymentId)
             } else if(result.paymentIntent.status === 'requires_action'){

                 const { error: errorAction, paymentIntent } =
                     await stripe.handleCardAction(result.paymentIntent.client_secret);

                 if(errorAction){
                     //handle 3d error
                 }else{
                     orderConfirmationRequest(paymentIntent.id);
                 }
             }
         }
     };

     const orderConfirmationRequest = (paymentIntentId) => {
         setError(null);
         setProcessing(false);
         setSucceeded(true);
         const CONFIRM_ORDER_API_URL = '/awf/ecommerce/rest/payment/'+paymentIntentId;
         const orderConfirmation = async (cartUrl) => {
             try {

                 const res = await axios.get(cartUrl)
                 window.location.href = "/order#"+res.data;

             }catch (err){
                 console.log(err)
             }
         }
         orderConfirmation(CONFIRM_ORDER_API_URL).then()
     }

    return (
        <form id="payment-form" onSubmit={handleSubmit}>
            <CardElement id="card-element" options={cardStyle} onChange={handleChange} />
            <button
                disabled={processing || disabled || succeeded}
                id="submit"
            >
        <span id="button-text">
          {processing ? (
              <div className="spinner" id="spinner"/>
          ) : (
              "Pay now"
          )}
        </span>
            </button>
            {/* Show any error that happens when processing the payment */}
            {error && (
                <div className="card-error" role="alert">
                    {error}
                </div>
            )}
        </form>
    );
}
export default CheckoutForm;