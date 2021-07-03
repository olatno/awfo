package com.ecommerce.awf.repository.commerce;

import com.ecommerce.awf.model.commerce.Category;
import com.ecommerce.awf.model.commerce.ProductGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The Brand repository class
 *
 * @author ola
 * @since 14/11/2020.
 */

public interface ProductGroupRepository extends JpaRepository<ProductGroup, Integer> {

    List<ProductGroup> findProductGroupByCategory(Category category);

    ProductGroup findByName(String name);
}

