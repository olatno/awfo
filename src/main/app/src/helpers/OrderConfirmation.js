import React from "react";
import {renderHTML} from 'react-render-html';
const OrderConfirmation = (props) => {
    const {pros} = props;
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

    return renderHTML("<a class='github' href='https://github.com'><b>GitHub</b></a>")
}
export default OrderConfirmation;