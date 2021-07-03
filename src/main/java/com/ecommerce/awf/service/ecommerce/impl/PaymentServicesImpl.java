package com.ecommerce.awf.service.ecommerce.impl;

import com.ecommerce.awf.model.checkout.Cart;
import com.ecommerce.awf.model.checkout.ProductItem;
import com.ecommerce.awf.model.commerce.WeightList;
import com.ecommerce.awf.repository.checkout.CustomerOrderRepository;
import com.ecommerce.awf.service.ecommerce.PaymentServices;
import com.ecommerce.awf.service.rest.CartServices;
import com.ecommerce.awf.util.CustomerParameters;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.model.ShippingDetails;
import com.stripe.net.RequestOptions;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.CustomerUpdateParams;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.stripe.net.ApiResource.GSON;

@Service
public class PaymentServicesImpl implements PaymentServices {

    private CartServices cartServices;
    private CustomerOrderRepository customerOrderRepository;

    @Autowired
    public PaymentServicesImpl(CartServices cartServices, CustomerOrderRepository customerOrderRepository){
        this.cartServices = cartServices;
        this.customerOrderRepository = customerOrderRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String stripeClientSecret(PaymentIntentCreateParams params, RequestOptions options) throws StripeException {

        PaymentIntent intent = PaymentIntent.create(params, options);
        return intent.getClientSecret();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CustomerCreateParams createStripeCustomerParams(CustomerParameters params){
        CustomerCreateParams stripParams = CustomerCreateParams.builder().setAddress(
                CustomerCreateParams.Address.builder().
                        setCity(params.getAddress().getBilling().getCity()).
                        setCountry(params.getAddress().getBilling().getCountry()).
                        setLine1(params.getAddress().getBilling().getLineOne()).
                        setLine2(params.getAddress().getBilling().getLineTwo()).
                        setState(params.getAddress().getBilling().getState()).
                        setPostalCode(params.getAddress().getBilling().getPostalCode()).build()).
                setShipping(CustomerCreateParams.Shipping.builder().
                        setName( params.getAddress().getDelivery().getName()).
                        setPhone(params.getAddress().getDelivery().getPhone()).
                        setAddress(CustomerCreateParams.Shipping.Address.builder().
                                setCity( params.getAddress().getDelivery().getCity()).
                                setCountry( params.getAddress().getDelivery().getCountry()).
                                setLine1(params.getAddress().getDelivery().getLineOne()).
                                setLine2(params.getAddress().getDelivery().getLineTwo()).
                                setState(params.getAddress().getDelivery().getState()).
                                setPostalCode(params.getAddress().getDelivery().getPostalCode()).
                                build()).
                        build()).
                setEmail(params.getAddress().getBilling().getEmail()).
                setDescription(params.getDescription()).
                setName(params.getAddress().getBilling().getName()).
                setPhone(params.getAddress().getBilling().getPhone()).
                build();
        return stripParams;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long cartAmount(Cart cart){
        BigDecimal total = BigDecimal.ZERO;
        List<ProductItem> productItems = new ArrayList<>(cart.getProductItems());
        for(ProductItem productItem : productItems){
            List<WeightList> weightLists = new ArrayList<>(productItem.getProduct().getProductWeight().getWeightList());
            BigDecimal quantity = new BigDecimal(productItem.getQuantity());
            BigDecimal price = weightLists.get(productItem.getWeightListIndex()).getPrice().getValue();
            BigDecimal quantityWeight = quantity.multiply(price);
            total = total.add(quantityWeight);
        }
        return (total.longValue() * 100);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PaymentIntentCreateParams createPaymentIntentParam(Long amount, Customer customer, String currency, ShippingDetails shipping){

        PaymentIntentCreateParams.Shipping.Address address = PaymentIntentCreateParams.Shipping.Address.builder()
                .setCity(shipping.getAddress().getCity())
                .setCountry(shipping.getAddress().getCountry())
                .setLine1(shipping.getAddress().getLine1())
                .setLine2(shipping.getAddress().getLine2())
                .setPostalCode(shipping.getAddress().getPostalCode())
                .setState(shipping.getAddress().getState())
                .build();
        PaymentIntentCreateParams.Shipping intentShipping = PaymentIntentCreateParams.Shipping.builder()
                .setAddress(address)
                .setName(shipping.getName())
                .setPhone(shipping.getPhone())
                .build();

              return   PaymentIntentCreateParams.builder()
                        .setCurrency(currency)
                        .setAmount(amount)
                        .setCustomer(customer.getId())
                        .setShipping(intentShipping)
                        .setReceiptEmail(customer.getEmail())
                        .build();
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public Customer getCustomer(String customer, RequestOptions options) throws StripeException {
        return Customer.retrieve(customer, options);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CustomerUpdateParams createCustomerUpdateParam(CustomerParameters params){

        final CustomerUpdateParams build = CustomerUpdateParams.builder()
                .setAddress(CustomerUpdateParams.Address.builder()
                        .setCity(params.getAddress().getBilling().getCity())
                        .setCountry(params.getAddress().getBilling().getCountry())
                        .setLine1(params.getAddress().getBilling().getLineOne())
                        .setLine2(params.getAddress().getBilling().getLineTwo())
                        .setPostalCode(params.getAddress().getBilling().getPostalCode())
                        .setState(params.getAddress().getBilling().getState())
                        .build())
                .setShipping(CustomerUpdateParams.Shipping.builder()
                        .setAddress(CustomerUpdateParams.Shipping.Address.builder()
                                .setCity(params.getAddress().getDelivery().getCity())
                                .setCountry(params.getAddress().getDelivery().getCountry())
                                .setLine1(params.getAddress().getDelivery().getLineOne())
                                .setLine2(params.getAddress().getDelivery().getLineTwo())
                                .setPostalCode(params.getAddress().getDelivery().getPostalCode())
                                .setState(params.getAddress().getDelivery().getState())
                                .build())
                                .setName(params.getAddress().getDelivery().getName())
                                .setPhone(params.getAddress().getDelivery().getPhone())
                        .build())
                .setEmail(params.getAddress().getBilling().getEmail())
                .setName(params.getAddress().getBilling().getName())
                .build();
        return build;
    }
    /**
     * {@inheritDoc}
     */
    public void updatePaymentIntent(Map<String, String> paymentIntentInfo, Cart cart, RequestOptions options, CustomerParameters params, String cartPaymentIntentId, Long amount) throws StripeException {
        Customer stripeCustomer = this.getCustomer(cart.getCustomer().getStripeId(), options);
        CustomerUpdateParams customerUpdateParams = this.createCustomerUpdateParam(params);
        stripeCustomer.update(customerUpdateParams, options);
        PaymentIntent paymentIntent = PaymentIntent.retrieve(cartPaymentIntentId, options);
        paymentIntent.setAmount(amount);
        paymentIntentInfo.put("clientSecret", paymentIntent.getClientSecret());
        paymentIntentInfo.put("paymentIntentId", cartPaymentIntentId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createPaymentIntent(Map<String, String> paymentIntentInfo, Cart cart, RequestOptions options, CustomerParameters params, Long amount) throws StripeException {

        CustomerCreateParams stripParams = this.createStripeCustomerParams(params);
        Customer stripeCustomer = Customer.create(stripParams, options);
        Customer customer = GSON.fromJson(stripeCustomer.toJson(), Customer.class);

        com.ecommerce.awf.model.checkout.Customer checkoutCustomer =
                com.ecommerce.awf.model.checkout.Customer.builder().
                        email(customer.getEmail()).
                        stripeId(customer.getId()).build();
        com.ecommerce.awf.model.checkout.Customer createdCustomer = cartServices.createCustomer(checkoutCustomer);
        PaymentIntentCreateParams paymentIntentCreateParams = this.createPaymentIntentParam(amount, stripeCustomer, "gbp", stripeCustomer.getShipping());

        PaymentIntent intent = PaymentIntent.create(paymentIntentCreateParams, options);
        paymentIntentInfo.put("clientSecret", intent.getClientSecret());
        paymentIntentInfo.put("paymentIntentId", intent.getId());
        cart.setCustomer(createdCustomer);
        cart.setPaymentIntentId(intent.getId());
        cartServices.updateCart(cart);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RequestOptions stripeRequestOption(String key){
        return RequestOptions.builder()
                        .setApiKey(key)
                        .build();
    }
}
