package com.ecommerce.awf.controller.rest;

import com.ecommerce.awf.controller.page.Ecommerce;
import com.ecommerce.awf.model.commerce.Brand;
import com.ecommerce.awf.model.commerce.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestEcommerceRest {

    @Autowired
    private EcommerceRest ecommerceRest;

    @Test
    public void testAllProducts(){
       ResponseEntity<List<Product>> productList = ecommerceRest.allProducts();
       Assert.assertEquals(productList.getStatusCode().value(), 200);
       Assert.assertEquals(productList.getStatusCode().name(), "OK");
       Assert.assertTrue(productList.getBody().size() > 0);
       Assert.assertEquals(productList.getBody().stream().findAny().get().getName(), "Ewura yam product");
       Assert.assertEquals(productList.getBody().stream().findFirst().get().getCountry(), null);
    }

    @Test
    public void testGetBrands(){
      ResponseEntity<List<Brand>> brandList = ecommerceRest.getBrands();
        Assert.assertEquals(brandList.getStatusCode().value(), 200);
        Assert.assertEquals(brandList.getStatusCode().name(), "OK");
        Assert.assertTrue(brandList.getBody().size() > 0);
    }
}
