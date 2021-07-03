package com.ecommerce.awf.repository.commerce;

import com.ecommerce.awf.model.commerce.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The Promotion repository class
 *
 * @author ola
 * @since 14/11/2020.
 */

public interface PromotionRepository extends JpaRepository<Promotion, Integer> {
}
