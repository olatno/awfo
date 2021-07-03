package com.ecommerce.awf.service.rest;

import com.ecommerce.awf.model.checkout.Cart;
import com.ecommerce.awf.model.checkout.Customer;
import com.ecommerce.awf.model.checkout.ProductItem;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CartServices {

    /**
     * Create cart
     *
     * @return the created cart
     */
    Cart createCart(Cart cart);

    /**
     * Find cart by cookie value
     *
     * @return the created cart
     */
    Cart findCartByCookieValue(String cookieValue);

    /**
     * Find cart by stripe secret
     *
     * @return the created cart
     */
    Cart findCartByPaymentIntentId(String stripeSecret);

    /**
     * Create cart
     *
     * @return the created cart
     */
    Cart findCartByCookieValue(HttpServletRequest request);

    /**
     * Update cart
     *
     * @return updated cart
     */
    Cart updateCart(Cart cart);

    /**
     * Create ProductItem
     *
     * @return the created ProductItem
     */
    ProductItem createProductItem(ProductItem productItem);

    /**
     * Update ProductItem
     *
     * @return updated ProductItem
     */
    ProductItem updateProductItem(ProductItem productItem);

    /**
     * The List of  ProductItem
     *
     * @return List of ProductItem by cart
     */
    List<ProductItem> findProductItems(Cart cart);

    /**
     * Update ProductItem
     *
     * @return updated ProductItem
     */
    ProductItem findProductItem(Integer id);

    /**
     * Remove ProductItem
     *
     * @return void
     */
    void removeProductItem(Integer id);

    /**
     * Create cart
     *
     * @return the created cart
     */
    Customer createCustomer(Customer customer);
}
