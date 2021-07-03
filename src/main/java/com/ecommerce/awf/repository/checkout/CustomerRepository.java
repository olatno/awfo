package com.ecommerce.awf.repository.checkout;

import com.ecommerce.awf.model.checkout.Cart;
import com.ecommerce.awf.model.checkout.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The Customer repository class
 *
 * @author ola
 * @since 21/02/2021.
 */

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
