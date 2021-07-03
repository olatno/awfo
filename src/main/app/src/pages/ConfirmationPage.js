import React, {Component, useEffect, useState} from "react";
import Header from "../structure/Header";
import BannerSmall from "../structure/BannerSmall";
import Footer from "../structure/Footer";
import ReactDOM from "react-dom";
import { renderToString } from "react-dom/server";
import axios from "axios";

const ConfirmationPage = (props) => {

    const [cart, setCart] = useState('');
    const [stripeCustomer, setStripeCustomer] = useState('');
    const [orderNumber] = useState(props.location.hash.replace('#', ''));
    const [ loading, setLoading ] = useState(false);
    const [paymentMethod, setPaymentMethod]   = useState('');
    const [emailSend, setEmailSend]   = useState('');
    const [amount, setAmount]   = useState('');

    useEffect(() => {
        console.log('use effect')
            const CONFIRMATION_REST_API_URL = '/awf/ecommerce/rest/confirmation/'+orderNumber;
            const confirmationInfo = async () => {
                setLoading(true)
                try {
                    const res = await axios.get(CONFIRMATION_REST_API_URL)
                    if(res.data.cart !== undefined && res.data.customer !== undefined) {
                        setCart(res.data.cart);
                        setStripeCustomer(res.data.customer)
                        setPaymentMethod(res.data.paymentMethod)
                        setAmount(res.data.amount)
                        const cartHtml = res.data.cart.productItems.map((orderedItem, index) => {
                            return renderToString(<p>{orderedItem.product.name}</p>)
                        })
                        const email = htmlTemplate(cartHtml, res.data.customer, res.data.amount, res.data.paymentMethod)
                        orderEmailConfirmation(email.replace(",", " "), res.data.customer.email, orderNumber)
                        console.log(email)
                    }
                }catch (err){
                    console.log(err)
                }
                setLoading(false)
            }
            confirmationInfo().then()
    }, []);

    const orderEmailConfirmation = (emailContent, emailTo, orderConfirmation) =>{
        const ORDER_CONFIRMATION_REST_API_URL = '/awf/ecommerce/rest/orderEmailConfirmation';
        const data = {
            emailContent:emailContent,
            emailTo:emailTo,
            orderConfirmation:orderConfirmation
        }
        const sendOrderConfirmation = async () => {
            try {
                const res = await axios.post(ORDER_CONFIRMATION_REST_API_URL, data)
                setEmailSend(res.data);
            }catch (err){
                console.log(err)
            }
        }
        sendOrderConfirmation().then()
    }

    function htmlTemplate(cart, customer, amount, paymentMethod) {
        return `
        <!DOCTYPE html>
        <html>
        <head>
            <meta charset="utf-8">
            <title>Order confirmation: ${orderNumber}</title>
        </head>
        
        <body>
       
        <div>Product info
            ${cart}
           
        </div>
        
                <div>Payment method and total price ${paymentMethod} / ${amount}</div>
                <div>delivery contact name
                           
        <p>${customer.shipping.name !== '' ? customer.shipping.name : ''}
            <br/>${customer.shipping.phone !== '' ? customer.shipping.phone : ''}
            <br/> ${customer.email !== '' ? customer.email : ''}
        </p>
        
                </div>
                <div>delivery address
                      
        <p> ${ customer.shipping.address.line1}, ${customer.shipping.address.line2}, ${customer.shipping.address.city}, ${customer.shipping.address.state}, ${customer.shipping.address.postalCode}, ${customer.shipping.address.country}</p>
        
                </div>
        </body>
        </html>
    `;
    }
        return(
            <div>
                <Header confirmation={true}/>
                <BannerSmall/>
                <div className="container">
                <div className="row">
                    <div className="col-md-12">
                        <article className="card order-group">
                            <header className="card-header">
                                <b className="d-inline-block mr-3">Confirmation number: {orderNumber}</b>
                                {cart !== '' && <span>Date: {cart.created}</span> }
                            </header>
                            <div className="card-body">
                                {cart !== '' &&
                                <ul className="row">
                                    {cart.productItems.map((orderedItem, index) => {
                                        return (
                                            <li className="col-md-4" key={index}>
                                                <figure className="itemside  mb-3">
                                                    <div className="aside"><img
                                                        src={orderedItem.product.gallery.images[0].path}
                                                        className="img-sm border"/></div>
                                                    <figcaption className="info align-self-center">
                                                        <p className="title">{orderedItem.product.name}</p>
                                                        <span
                                                            className="text-muted">${orderedItem.product.productWeight.weightList[orderedItem.weightListIndex].price.value * orderedItem.quantity}</span>
                                                    </figcaption>
                                                </figure>
                                            </li>
                                        )
                                    })
                                    }
                                </ul>
                                }
                                <hr/>
                                <div className="row">
                                    <div className="col-md-4">
                                        <h6 className="text-muted">Payment</h6>
                                        {paymentMethod !== '' &&
                                        <span className="text-success">
				                            <i className="fab fa-lg fa-cc-visa"></i>
                                            {paymentMethod}
			                            </span>
                                        }
                                        {amount !== '' &&
                                            <p>Subtotal: ${amount} <br/>
                                            Shipping fee: $56 <br/>
                                            <span className="b">Total:  ${amount} </span>
                                            </p>
                                        }
                                    </div>
                                    <br/>
                                    <div className="col-md-4">
                                        <h6 className="text-muted">Contact</h6>
                                        { stripeCustomer !== '' &&
                                            <p>{stripeCustomer.shipping.name !== undefined ? stripeCustomer.shipping.name : ''}
                                                <br/>{stripeCustomer.shipping.phone !== undefined ? stripeCustomer.shipping.phone : ''}
                                                <br/> {stripeCustomer.email !== undefined ? stripeCustomer.email : ''}
                                            </p>
                                        }
                                    </div>
                                    <div className="col-md-4">
                                        <h6 className="text-muted">Shipping address</h6>
                                        {stripeCustomer !== '' &&
                                            <p> { stripeCustomer.shipping.address.line1}, {stripeCustomer.shipping.address.line2}, {stripeCustomer.shipping.address.city}, {stripeCustomer.shipping.address.state}, {stripeCustomer.shipping.address.postalCode}, {stripeCustomer.shipping.address.country}</p>
                                        }
                                    </div>

                                    <div className="col-md-4">
                                        <h6 className="text-muted">Email order confirmation</h6>
                                        { emailSend !== '' &&
                                        <p>{emailSend}</p>
                                        }
                                    </div>
                                </div>

                            </div>

                        </article>
                    </div>

                </div>
                </div>
                <Footer/>
            </div>
        );
};
export default ConfirmationPage;