package com.ecommerce.awf.service.rest.impl;

import com.ecommerce.awf.model.commerce.Brand;
import com.ecommerce.awf.model.commerce.Category;
import com.ecommerce.awf.model.commerce.Product;
import com.ecommerce.awf.model.commerce.ProductGroup;
import com.ecommerce.awf.repository.commerce.BrandRepository;
import com.ecommerce.awf.repository.commerce.CategoryRepository;
import com.ecommerce.awf.repository.commerce.ProductGroupRepository;
import com.ecommerce.awf.repository.commerce.ProductRepository;
import com.ecommerce.awf.service.rest.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProductServicesImpl implements ProductServices {

    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
    private final ProductGroupRepository productGroupRepository;

    @Autowired
    public ProductServicesImpl(ProductRepository productRepository, BrandRepository brandRepository, CategoryRepository categoryRepository, ProductGroupRepository productGroupRepository) {
        this.productRepository = productRepository;
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
        this.productGroupRepository = productGroupRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Product> allProducts() {
        return productRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Brand> allBrands() {
        return brandRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Product> popularProducts() {
        Random rand = new Random();
        List<Product> productList = this.allProducts();
        List<Product> randomProduct = rand.
                ints(9, 0, productList.size()).distinct().
                mapToObj(productList::get).
                collect(Collectors.toList());
        return randomProduct;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Category> allCategories(){
        return categoryRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductGroup> popularGroups(){
        Random rand = new Random();
        List<ProductGroup> productGroups = productGroupRepository.findAll();
        List<ProductGroup> randomProductGroup = rand.
                ints(8, 0, productGroups.size()).distinct().
                mapToObj(productGroups::get).
                collect(Collectors.toList());
        return randomProductGroup;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Product> getProductsByGroup(ProductGroup productGroup){
        return new ArrayList<>(productGroup.getProducts());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Product> getProductsByCategory(Category category){
        List<Product> products = new ArrayList<>();
        List<ProductGroup> productGroups = new ArrayList<>(category.getProductGroups());

        for(ProductGroup productGroup : productGroups){
            List<Product> productList = new ArrayList<>(productGroup.getProducts());
            for(Product product : productList){
                products.add(product);
            }
        }
        return products;
    }

    /**
     * {@inheritDoc}
     */
    public Category getCategory(String name){
        return categoryRepository.findByName(name);
    }

    /**
     * {@inheritDoc}
     */
    public ProductGroup getProductGroup(String name){
        return productGroupRepository.findByName(name);
    }
}