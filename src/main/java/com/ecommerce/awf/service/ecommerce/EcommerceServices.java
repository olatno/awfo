package com.ecommerce.awf.service.ecommerce;

import com.ecommerce.awf.model.checkout.Cart;
import com.ecommerce.awf.model.checkout.CustomerOrder;
import com.ecommerce.awf.model.checkout.ProductItem;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface EcommerceServices {

    /**
     * Create cart
     *
     * @return the created cart
     */
    Cart getCart(HttpServletRequest request);

    /**
     * The List of  ProductItem
     *
     * @return List of ProductItem by cart
     */
    List<ProductItem> getProductItemList(HttpServletRequest request);

    /**
     * Get ProductItem
     *
     * @return get ProductItem
     */
    ProductItem getProductItem(String requestProductItemId);

    /**
     * Random generator
     *
     * @return get ProductItem
     */
    String randomValueGenerator(int position, String prefix);

    /**
     * CustomerOrder createOder
     *
     * @return created customerOrder
     */

    CustomerOrder createOrder(CustomerOrder customerOrder);

    /**
     * Find customer order by order confirmation
     *
     * @return CustomerOrder
     */
    CustomerOrder findCustomerOrderByOrderConfirmation(String custOrder);
}
