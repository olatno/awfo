package com.ecommerce.awf.repository.commerce;

import com.ecommerce.awf.model.commerce.Price;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The Price repository class
 *
 * @author ola
 * @since 14/11/2020.
 */

public interface PriceRepository extends JpaRepository<Price, Integer> {
}
