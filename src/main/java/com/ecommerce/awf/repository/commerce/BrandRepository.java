package com.ecommerce.awf.repository.commerce;

import com.ecommerce.awf.model.commerce.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The Brand repository class
 *
 * @author ola
 * @since 14/11/2020.
 */

public interface BrandRepository extends JpaRepository<Brand, Integer> {
}
