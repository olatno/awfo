package com.ecommerce.awf.repository.admin;

import com.ecommerce.awf.model.admin.Image;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The Image repository class
 *
 * @author ola
 * @since 14/11/2020.
 */

public interface ImageRepository extends JpaRepository<Image, Integer> {
}
