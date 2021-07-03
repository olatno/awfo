package com.ecommerce.awf.repository.checkout;

import com.ecommerce.awf.model.checkout.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The CustomerOrder repository class
 *
 * @author ola
 * @since 21/02/2021.
 */

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Integer> {
    CustomerOrder findCustomerOrderByOrderConfirmation(String orderConfirmation);
}
