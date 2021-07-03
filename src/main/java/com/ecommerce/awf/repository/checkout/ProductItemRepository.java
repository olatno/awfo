package com.ecommerce.awf.repository.checkout;

import com.ecommerce.awf.model.checkout.Cart;
import com.ecommerce.awf.model.checkout.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * The ProductItem repository class
 *
 * @author ola
 * @since 21/02/2021.
 */

public interface ProductItemRepository extends JpaRepository<ProductItem, Integer> {

    @Query("SELECT p FROM ProductItem p WHERE p.cart = :cart")
    List<ProductItem> findProductItemsByCart(@Param("cart") Cart cart);

    @Query("SELECT p FROM ProductItem p WHERE p.id = :id")
    ProductItem findProductItemById(@Param("id") Integer id);
}
