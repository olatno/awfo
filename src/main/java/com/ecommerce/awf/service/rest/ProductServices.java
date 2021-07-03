package com.ecommerce.awf.service.rest;

import com.ecommerce.awf.model.commerce.Brand;
import com.ecommerce.awf.model.commerce.Category;
import com.ecommerce.awf.model.commerce.Product;
import com.ecommerce.awf.model.commerce.ProductGroup;

import java.util.List;

public interface ProductServices {

    /**
     * Get all products
     *
     * @return the list of products
     */
    List<Product>   allProducts();

    /**
     * Get all products that are recently added filter by date added
     *
     * @return the list of products recently added
     */
    List<Product>   popularProducts();

    /**
     * Get all brands
     *
     * @return the list of brands
     */
    List<Brand>   allBrands();

    /**
     * Get all categories
     *
     * @return the list of categories
     */
    List<Category>   allCategories();

    /**
     * Get popular groups
     *
     * @return the list of popular groups
     */
    List<ProductGroup>   popularGroups();

    /**
     * Get products in category
     *
     * @return the list of products in a category
     */
    List<Product> getProductsByCategory(Category category);

    /**
     * Get products in product group
     *
     * @return the list of products in a group
     */
    List<Product> getProductsByGroup(ProductGroup productGroup);

    /**
     * Get category by name
     *
     * @return the category
     */
    Category getCategory(String name);

    /**
     * Get product group by name
     *
     * @return the products group
     */
    ProductGroup getProductGroup(String name);
}
