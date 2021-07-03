//package com.ecommerce.awf.builder;
//
//import com.ecommerce.awf.enums.ProductStatus;
//import com.ecommerce.awf.model.admin.Country;
//
//public class CountryBuilder {
//    private String name;
//    private ProductStatus productStatus;
//
//    public CountryBuilder setName(String name) {
//        this.name = name;
//        return this;
//    }
//
//    public CountryBuilder setProductStatus(ProductStatus productStatus) {
//        this.productStatus = productStatus;
//        return this;
//    }
//
//    public Country createCountry() {
//        return new Country(name, productStatus);
//    }
//}