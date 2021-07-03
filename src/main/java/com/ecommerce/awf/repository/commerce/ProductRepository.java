package com.ecommerce.awf.repository.commerce;

import com.ecommerce.awf.model.admin.Country;
import com.ecommerce.awf.model.commerce.Product;
import com.ecommerce.awf.model.commerce.ProductGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * The Product repository class
 *
 * @author ola
 * @since 14/11/2020.
 */

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p WHERE p.created <= :futureDate")
    List<Product> recentlyAdded(@Param("futureDate") LocalDate futureDate);

    List<Product> findByProductGroup(ProductGroup productGroup);
}
