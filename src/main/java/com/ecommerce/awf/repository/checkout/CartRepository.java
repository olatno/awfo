package com.ecommerce.awf.repository.checkout;

import com.ecommerce.awf.model.checkout.Cart;
import com.ecommerce.awf.model.commerce.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The Gallery repository class
 *
 * @author ola
 * @since 21/02/2021.
 */

public interface CartRepository extends JpaRepository<Cart, Integer> {

    Cart findCartByCookie(String cookieValue);
    Cart findCartByPaymentIntentId(String paymentIntentId);
}
