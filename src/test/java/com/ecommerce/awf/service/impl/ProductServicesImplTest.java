package com.ecommerce.awf.service.impl;

import com.ecommerce.awf.model.commerce.Brand;
import com.ecommerce.awf.repository.commerce.BrandRepository;
import com.ecommerce.awf.service.rest.ProductServices;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServicesImplTest {

    @Autowired
    ProductServices productServices;
    @Autowired
    BrandRepository brandRepository;

    @Test
    public void testAllBrands(){
        createBrand();
        List<Brand> brandList = productServices.allBrands();
        Assert.assertEquals(brandList.size() , 6);
    }

    private void createBrand(){
        final Brand band1 = Brand.builder().name("Brand one").logoPath("/home/olatno/Pictures/brand").build();
        final Brand band2 = Brand.builder().name("Brand two").logoPath("/home/olatno/Pictures/brand").build();
        Stream.of(band1,band2).forEach(brandRepository::save);
    }
}
