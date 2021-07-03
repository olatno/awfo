package com.ecommerce.awf.controller.rest;

import com.ecommerce.awf.model.checkout.Cart;
import com.ecommerce.awf.model.checkout.CustomerOrder;
import com.ecommerce.awf.service.ecommerce.EcommerceServices;
import com.ecommerce.awf.service.ecommerce.PaymentServices;
import com.ecommerce.awf.service.mail.EmailSenderServices;
import com.ecommerce.awf.service.rest.CartServices;
import com.ecommerce.awf.util.CookieUtils;
import com.ecommerce.awf.util.CustomerParameters;
import com.ecommerce.awf.util.EmailParameters;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.net.RequestOptions;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static com.stripe.net.ApiResource.GSON;

@Configuration
@RestController
@RequestMapping("/awf/ecommerce/rest")
public class PaymentRest {
    private static final Logger LOG = LoggerFactory.getLogger(PaymentRest.class);

    private final PaymentServices paymentServices;
    private final CartServices cartServices;
    private final EcommerceServices ecommerceServices;
    private final EmailSenderServices emailSender;

    private final String AWF = "AWF";
    private final String CARD_COOKIE_ID = "CartID";
    private final int BASE_RATE = 100;

    @Value("${stripe.key.secret.test}")
    private String stripSK;

    @Autowired
    public PaymentRest(PaymentServices paymentServices, CartServices cartServices, EcommerceServices ecommerceServices, EmailSenderServices emailSender) {
        Assert.notNull(paymentServices, "ProductServices must not be null!");
        this.paymentServices = paymentServices;
        this.cartServices  = cartServices;
        this.ecommerceServices = ecommerceServices;
        this.emailSender = emailSender;
    }

    @PostMapping("/createCustomer")
    ResponseEntity<Map<String, String>> createCustomer(@RequestBody CustomerParameters params, HttpServletRequest request) throws StripeException {

        Map<String, String> paymentInform  = new HashMap<>();
        RequestOptions requestOptions = paymentServices.stripeRequestOption(stripSK);
        try {
            Cart cart = cartServices.findCartByCookieValue(request);
            Long amount = paymentServices.cartAmount(cart);
            String cartPaymentIntentId = cart.getPaymentIntentId();

            if (!StringUtils.isEmpty(cartPaymentIntentId)) {

                paymentServices.updatePaymentIntent(paymentInform, cart, requestOptions, params, cartPaymentIntentId, amount);
            } else {

                paymentServices.createPaymentIntent(paymentInform, cart, requestOptions, params, amount);
            }

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.ALL_VALUE)
                    .body(paymentInform);
        } catch (InvalidRequestException i) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.ALL_VALUE)
                    .body(paymentInform);
        }
    }

    @GetMapping("/payment/{paymentId}")
    ResponseEntity<Object> createPayment(@PathVariable String paymentId , HttpServletRequest request, HttpServletResponse response){
        Cart cart = cartServices.findCartByPaymentIntentId(paymentId);
        RequestOptions requestOptions = paymentServices.stripeRequestOption(stripSK);
        try {
            PaymentIntent paymentIntent = PaymentIntent.retrieve(cart.getPaymentIntentId(), requestOptions);
            PaymentIntent jsonPaymentIntent = GSON.fromJson(paymentIntent.toJson(), PaymentIntent.class);

            CustomerOrder customerOrder = CustomerOrder.builder()
                    .created(LocalDate.now())
                    .cart(cart)
                    .orderConfirmation(ecommerceServices.randomValueGenerator(8, AWF).toUpperCase())
                    .cardType(jsonPaymentIntent.getCharges().getData().get(0).getPaymentMethodDetails().getCard().getBrand())
                    .build();

            CustomerOrder createdOrder = ecommerceServices.createOrder(customerOrder);
            CookieUtils.clearCookies(request, response, CARD_COOKIE_ID);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.ALL_VALUE)
                    .body(createdOrder.getOrderConfirmation());
        }catch (Exception e){
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.ALL_VALUE)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/confirmation/{confirmationId}")
    ResponseEntity<Map<String, Object>> createConfirmation(@PathVariable String confirmationId ){

        Map<String, Object> map = new HashMap<>();
        RequestOptions requestOptions = paymentServices.stripeRequestOption(stripSK);
        CustomerOrder customerOrder = ecommerceServices.findCustomerOrderByOrderConfirmation(confirmationId);
        Cart cart = customerOrder.getCart();
        Long amount = paymentServices.cartAmount(cart);

        map.put("cart", cart);
        map.put("amount", amount/BASE_RATE);
        map.put("paymentMethod", customerOrder.getCardType());
        com.ecommerce.awf.model.checkout.Customer customer = cart.getCustomer();
        Customer stripeCustomer;
        try {
            stripeCustomer = Customer.retrieve(customer.getStripeId(), requestOptions);
            Customer jsonCustomer = GSON.fromJson(stripeCustomer.toJson(), Customer.class);
            map.put("customer", jsonCustomer);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.ALL_VALUE)
                    .body(map);
        }catch (Exception e){
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.ALL_VALUE)
                    .body(map);
        }
    }

    @PostMapping("/orderEmailConfirmation")
    ResponseEntity<String> sendOrderConfirmation(@RequestBody EmailParameters params){
        String subject = StringUtils.EMPTY;
        try {
            subject = "Order confirmation: " +params.getOrderConfirmation();
            emailSender.sendEmail(subject, params.getEmailContent(), params.getEmailTo());
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.ALL_VALUE)
                    .body("Order confirmation sent to : " +params.getEmailTo());
        }catch (Exception e){
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.ALL_VALUE)
                    .body("Order confirmation sent email failed");
        }
    }

}
