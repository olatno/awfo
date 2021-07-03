package com.ecommerce.awf.service.ecommerce.impl;

import com.ecommerce.awf.model.checkout.Cart;
import com.ecommerce.awf.model.checkout.CustomerOrder;
import com.ecommerce.awf.model.checkout.ProductItem;
import com.ecommerce.awf.repository.checkout.CustomerOrderRepository;
import com.ecommerce.awf.service.ecommerce.EcommerceServices;
import com.ecommerce.awf.service.rest.CartServices;
import com.ecommerce.awf.util.CookieUtils;
import com.stripe.model.PaymentIntent;
import com.stripe.param.CustomerUpdateParams;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

@Service
public class EcommerceServicesImpl implements EcommerceServices {

    private final String CARD_COOKIE_ID = "CartID";
    private final CartServices cartServices;
    private final CustomerOrderRepository orderRepository;

    @Autowired
    public EcommerceServicesImpl(CartServices cartServices, CustomerOrderRepository orderRepository){
        this.cartServices = cartServices;
        this.orderRepository = orderRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Cart getCart(HttpServletRequest request){
        String requestCookie = CookieUtils.getCookieValue(request , CARD_COOKIE_ID);
        Cart cart;
        Cart createdCart = cartServices.findCartByCookieValue(requestCookie);
        if(createdCart != null){
            cart = createdCart;
        }else {
            String generatedString = this.randomValueGenerator(30, null);
            Cart create = Cart.builder()
                    .created(LocalDate.now())
                    .cookie(generatedString)
                    .build();
            cart = cartServices.createCart(create);
        }
        return cart;
    }

    /**
     * {@inheritDoc}
     */
    public List<ProductItem> getProductItemList(HttpServletRequest request){
        String requestCookie = CookieUtils.getCookieValue(request , CARD_COOKIE_ID);
        Cart cart = cartServices.findCartByCookieValue(requestCookie);
        return cartServices.findProductItems(cart);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProductItem getProductItem(String requestProductItemId){
        Integer id = Integer.valueOf(requestProductItemId);
        return cartServices.findProductItem(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String randomValueGenerator(int position, String prefix){

        return StringUtils.isEmpty(prefix) ? RandomStringUtils.randomAlphanumeric(position) : prefix+RandomStringUtils.randomAlphanumeric(position);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CustomerOrder createOrder(CustomerOrder customerOrder){
        return orderRepository.save(customerOrder);
    }

    public CustomerOrder findCustomerOrderByOrderConfirmation(String custOrder){
        return orderRepository.findCustomerOrderByOrderConfirmation(custOrder);
    }

}
