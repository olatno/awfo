package com.ecommerce.awf.controller.rest;

import com.ecommerce.awf.helpers.ProductItemUpdateRequest;
import com.ecommerce.awf.model.checkout.Cart;
import com.ecommerce.awf.model.checkout.ProductItem;
import com.ecommerce.awf.model.commerce.Brand;
import com.ecommerce.awf.model.commerce.Category;
import com.ecommerce.awf.model.commerce.Product;
import com.ecommerce.awf.model.commerce.ProductGroup;
import com.ecommerce.awf.repository.checkout.CartRepository;
import com.ecommerce.awf.service.ecommerce.EcommerceServices;
import com.ecommerce.awf.service.rest.CartServices;
import com.ecommerce.awf.service.rest.ProductServices;
import com.ecommerce.awf.util.CookieUtils;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.net.RequestOptions;
import com.stripe.param.CustomerCreateParams;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.*;

@Configuration
@RestController
@RequestMapping("/awf/ecommerce/rest")
public class EcommerceRest {
    private static final Logger LOG = LoggerFactory.getLogger(EcommerceRest.class);

    private final String CATEGORY = "category";

    private final ProductServices productServices;
    private final CartServices cartServices;
    private final EcommerceServices ecommerceServices;

    @Autowired
    public EcommerceRest(ProductServices productServices, CartServices cartServices, EcommerceServices ecommerceServices) {
        Assert.notNull(productServices, "ProductServices must not be null!");
        this.productServices = productServices;
        this.cartServices  = cartServices;
        this.ecommerceServices = ecommerceServices;
    }

    @GetMapping("/products")
    ResponseEntity<List<Product>> allProducts() {
        List<Product> products = Collections.singletonList(new Product());
        try {
            products = productServices.allProducts();
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.ALL_VALUE)
                    .body(products);
        } catch (Exception ex) {
            LOG.error("Api Exception in all() method", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(products);
        }
    }

    @GetMapping("/popular")
    ResponseEntity<List<Product>> popularProduct(){
       List<Product> popularProducts = productServices.popularProducts();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.ALL_VALUE)
                .body(popularProducts);
    }

    @GetMapping("/productItems")
    ResponseEntity<List<ProductItem>> getProductItemsFromCart(HttpServletRequest request){
        List<ProductItem> productItems = ecommerceServices.getProductItemList(request);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.ALL_VALUE)
                .body(productItems);
    }

    @PostMapping("/cart")
    ResponseEntity<Cart> createProductCartItem(@RequestBody ProductItem productItem, HttpServletRequest request){
        Cart cart = ecommerceServices.getCart(request);
        productItem.setCart(cart);
        cartServices.createProductItem(productItem);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.ALL_VALUE)
                .body(cart);
    }

    @PostMapping("/updateProductItem")
    ResponseEntity<List<ProductItem>> updateCartItems(@RequestBody ProductItemUpdateRequest productItemUpdateRequest, HttpServletRequest request){
        ProductItem productItem = ecommerceServices.getProductItem(productItemUpdateRequest.getId());
        int quantity = productItem.getQuantity() + (productItemUpdateRequest.getQuantity());
        productItem.setQuantity(quantity);
        cartServices.updateProductItem(productItem);
        List<ProductItem> productItems = ecommerceServices.getProductItemList(request);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.ALL_VALUE)
                .body(productItems);
    }

    @PostMapping("/deleteProductItem")
    ResponseEntity<List<ProductItem>> deleteCartItems(@RequestBody ProductItemUpdateRequest productItemUpdateRequest, HttpServletRequest request){
        Integer id = Integer.valueOf(productItemUpdateRequest.getId());
        cartServices.removeProductItem(id);

        List<ProductItem> productItems = ecommerceServices.getProductItemList(request);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.ALL_VALUE)
                .body(productItems);
    }

    @GetMapping("/brands")
    ResponseEntity<List<Brand>> getBrands(){
        List<Brand> brandList = productServices.allBrands();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.ALL_VALUE)
                .body(brandList);
    }

    @GetMapping("/cartReview")
    ResponseEntity<Cart> getCart(HttpServletRequest request){
        Cart cart;
        try {
            cart = ecommerceServices.getCart(request);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.ALL_VALUE)
                    .body(cart);
        } catch (Exception ex) {
            LOG.error("Api Exception in getCart() method", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/groups")
    ResponseEntity<List<ProductGroup>> getPopularGroup(){
        List<ProductGroup> productGroups = productServices.popularGroups();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.ALL_VALUE)
                .body(productGroups);
    }

    @GetMapping("/categories")
    ResponseEntity<Map<String, Map<String, List<ProductGroup>>>> getCategories(){
        List<Category> categories = productServices.allCategories();
        Map<String,  Map<String, List<ProductGroup>>> map = new HashMap<>();
        Map<String, List<ProductGroup>> group = new HashMap<>();
        for(Category category : categories){
            group.put(category.getName(), new ArrayList<>(category.getProductGroups()));
        }
        map.put("categories", group);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.ALL_VALUE)
                .body(map);
    }

    @GetMapping("/categoriesAndGroups")
    ResponseEntity<Map<String, List<?>>> getCategoriesAndGroup(){
        Map<String, List<?>> map = new HashMap<>();
        List<Category> categories = productServices.allCategories();
        List<ProductGroup> productGroups = productServices.popularGroups();
        map.put("categories", categories);
        map.put("productGroups", productGroups);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.ALL_VALUE)
                .body(map);
    }

    @GetMapping("/products/{filter}")
    ResponseEntity<List<Product>> productFilter(@PathVariable String filter){
        String filterArray[] = filter.split("=");
        String filterBy = filterArray[0];
        String filterResult = filterArray[1];

        List<Product> productList = StringUtils.equals(filterBy, CATEGORY) ? productServices.getProductsByCategory(productServices.getCategory(filterResult)) :
                productServices.getProductsByGroup(productServices.getProductGroup(filterResult));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.ALL_VALUE)
                .body(productList);
    }

}
