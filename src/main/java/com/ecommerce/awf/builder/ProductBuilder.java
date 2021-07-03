//package com.ecommerce.awf.builder;
//
//import com.ecommerce.awf.model.admin.Country;
//import com.ecommerce.awf.model.commerce.*;
//
//public class ProductBuilder {
//
//
//    private String name;
//    private String description;
//    private Price price;
//    private Brand brand;
//    private Category category;
//    private Gallery gallery;
//    private Country country;
//    private ProductCode productCode;
//
//    public ProductBuilder setName(String name) {
//        this.name = name;
//        return this;
//    }
//
//    public ProductBuilder setDescription(String description) {
//        this.description = description;
//        return this;
//    }
//
//    public ProductBuilder setPrice(Price price) {
//        this.price = price;
//        return this;
//    }
//
//    public ProductBuilder setBrand(Brand brand) {
//        this.brand = brand;
//        return this;
//    }
//
//    public ProductBuilder setCategory(Category category) {
//        this.category = category;
//        return this;
//    }
//
//    public ProductBuilder setGallery(Gallery gallery) {
//        this.gallery = gallery;
//        return this;
//    }
//
//    public ProductBuilder setCountry(Country country) {
//        this.country = country;
//        return this;
//    }
//
//    public ProductBuilder setProductCode(ProductCode productCode) {
//        this.productCode = productCode;
//        return this;
//    }
//
//    public Product createProduct() {
//        return new Product(name, description, price, brand, category, gallery, country, productCode);
//    }
//}
