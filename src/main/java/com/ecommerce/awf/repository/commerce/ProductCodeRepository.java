package com.ecommerce.awf.repository.commerce;

import com.ecommerce.awf.model.admin.Country;
import com.ecommerce.awf.model.commerce.ProductCode;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The ProductCode repository class
 *
 * @author ola
 * @since 14/11/2020.
 */

public interface ProductCodeRepository extends JpaRepository<ProductCode, Integer> {
}
