package com.ecommerce.awf.repository.admin;

import com.ecommerce.awf.model.admin.PromotionType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The PromotionType repository class
 *
 * @author ola
 * @since 14/11/2020.
 */

public interface PromotionTypeRepository extends JpaRepository<PromotionType, Integer> {
}
