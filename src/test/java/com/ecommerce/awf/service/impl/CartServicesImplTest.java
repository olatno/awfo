package com.ecommerce.awf.service.impl;

import com.ecommerce.awf.model.checkout.Cart;
import com.ecommerce.awf.repository.checkout.CartRepository;
import com.ecommerce.awf.service.rest.CartServices;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartServicesImplTest {

    @Autowired
    CartServices cartServices;

    @Test
    public void testCreateCart(){
        String generatedCookie = RandomStringUtils.randomAlphanumeric(30);
        Cart cart = cartServices.createCart(this.createCart(generatedCookie));
        Assert.assertEquals(generatedCookie, cart.getCookie());
    }

    @Test
    public void testFindCartByCookieValue(){
        String generatedCookie = RandomStringUtils.randomAlphanumeric(30);
        Cart cart = cartServices.createCart(this.createCart(generatedCookie));
        Cart findByCookie = cartServices.findCartByCookieValue(generatedCookie);
        Assert.assertEquals(cart.getCookie(), findByCookie.getCookie());
    }

    private Cart createCart(String cookieValue){
        return Cart.builder().
                created(LocalDate.now()).
                cookie(cookieValue).
                build();
    }
}
