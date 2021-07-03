package com.ecommerce.awf.repository.commerce;

import com.ecommerce.awf.model.commerce.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The Category repository class
 *
 * @author ola
 * @since 14/11/2020.
 */

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Category findByName(String name);
}
