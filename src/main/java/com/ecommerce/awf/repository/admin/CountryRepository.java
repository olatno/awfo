package com.ecommerce.awf.repository.admin;

import com.ecommerce.awf.model.admin.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The Country repository class
 *
 * @author ola
 * @since 29/11/2020.
 */

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
}
