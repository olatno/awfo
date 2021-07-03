import axios from "axios";
import React from "react";

export const weightConverter = (value) =>{
    let convert = value/1000
    let converted = ''
    converted = convert >= 1 ? convert+'kg' : value+'g'
    return converted;
}

export const htmlTemplate = ( orderNumber ) => {
   const resultData = () =>{
       const CONFIRMATION_REST_API_URL = '/awf/ecommerce/rest/confirmation/'+orderNumber;
       const confirmationInfo = async () => {

           try {
               const res = await axios.get(CONFIRMATION_REST_API_URL)
               if(res.data.cart !== undefined && res.data.customer !== undefined) {
                    return res.data
               }
           }catch (err){
               console.log(err)
           }
       }
       confirmationInfo().then()
    }
    return `
        <!DOCTYPE html>
        <html>
        <head>
            <meta charset="utf-8">
            <title>Order confirmation: ${orderNumber}</title>
        </head>
        
        <body>
                 <div>Product info 
                  ${resultData.cart.productItems.map((orderedItem, index) => {
                       return <p>{orderedItem.product.name}</p>
                  })}
                  </div>
                <div>Payment and total price ${resultData.amount} / ${resultData.paymentMethod}</div>
                <div>delivery contact name
                           ${ resultData.cart.customer !== '' &&
                                            <p>{resultData.cart.customer.shipping.name !== undefined ? resultData.cart.customer.shipping.name : ''}
                                                <br/>{resultData.cart.customer.shipping.phone !== undefined ? resultData.cart.customer.shipping.phone : ''}
                                                <br/> {resultData.cart.customer.email !== undefined ? resultData.cart.customer.email : ''}
                                            </p>
                                        }
                </div>
                <div>delivery address
                        ${resultData.cart.customer !== '' &&
                                            <p> { resultData.cart.customer.shipping.address.line1}, {resultData.cart.customer.shipping.address.line2}, {resultData.cart.customer.shipping.address.city}, {resultData.cart.customer.shipping.address.state}, {resultData.cart.customer.shipping.address.postal_code}, {resultData.cart.customer.shipping.address.country}</p>
                                        }
                </div>
        </body>
        </html>
    `;
}