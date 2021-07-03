import React, {useState} from 'react';
import {useStripe, useElements, CardElement} from '@stripe/react-stripe-js';

import CardSection from '../helpers/CardSection';
import axios from "axios";
import Loader from "../widgets/Loader";

const CheckoutForms = (props) => {
    const stripe = useStripe();
    const elements = useElements();
    const [loading,setLoading] = useState(false)

    const handleSubmit = async (event) => {
        // We don't want to let default form submission happen here,
        // which would refresh the page.
        setLoading(true)
        event.preventDefault();

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
        const CONFIRM_ORDER_API_URL = '/awf/ecommerce/rest/payment/'+paymentIntentId;
        const orderConfirmation = async (cartUrl) => {
            try {

                const res = await axios.get(cartUrl)
              //  const email = htmlTemplate(res.data);
              //  console.log(email);
             //   const content = ReactDOMServer.renderToStaticMarkup(email);
                window.location.href = "/order#"+res.data;

            }catch (err){
                console.log(err)
            }
        }
        orderConfirmation(CONFIRM_ORDER_API_URL).then()
    }

    return (
        <form onSubmit={handleSubmit}>
            <CardSection />
            <button disabled={!stripe}>Confirm order</button>
            {loading ? <Loader/>: ''}
        </form>
    );
}
export default CheckoutForms;