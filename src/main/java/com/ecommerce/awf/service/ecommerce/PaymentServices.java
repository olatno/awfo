package com.ecommerce.awf.service.ecommerce;

import com.ecommerce.awf.model.checkout.Cart;
import com.ecommerce.awf.util.CustomerParameters;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.ShippingDetails;
import com.stripe.net.RequestOptions;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.CustomerUpdateParams;
import com.stripe.param.PaymentIntentCreateParams;

import java.util.Map;

public interface PaymentServices {

    /**
     * Get stripe client secret
     *
     * @return String
     */
    String stripeClientSecret(PaymentIntentCreateParams params, RequestOptions options) throws StripeException;

    /**
     * Create stripe customer parameters
     *
     * @return CustomerCreateParams
     */
    CustomerCreateParams createStripeCustomerParams(CustomerParameters params);

    /**
     * calculate cart amount
     *
     * @return Long
     */
    Long cartAmount(Cart cart);

    /**
     * Create payment intent parameters
     *
     * @return PaymentIntentCreateParams
     */
    PaymentIntentCreateParams createPaymentIntentParam(Long amount, Customer customer, String currency, ShippingDetails shipping);

    /**
     * Get stripe customer
     *
     * @return stripe customer
     */
    Customer getCustomer(String customer, RequestOptions options) throws StripeException;

    /**
     * Create customer update parameters
     *
     * @return CustomerUpdateParams
     */
    CustomerUpdateParams createCustomerUpdateParam(CustomerParameters params);
    /**
     * Update payment intent
     *
     * @return void
     */
    void updatePaymentIntent(Map<String, String> paymentIntentInfo, Cart cart, RequestOptions options, CustomerParameters params, String cartPaymentIntentId, Long amount) throws StripeException;

    /**
     * Create payment intent
     *
     * @return void
     */
    void createPaymentIntent(Map<String, String> paymentIntentInfo, Cart cart, RequestOptions options, CustomerParameters params, Long amount) throws StripeException;

    /**
     * Get stripe request option
     *
     * @return RequestOptions
     */
    RequestOptions stripeRequestOption(String key);
}
