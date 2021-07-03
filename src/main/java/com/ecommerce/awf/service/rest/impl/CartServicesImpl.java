package com.ecommerce.awf.service.rest.impl;

import com.ecommerce.awf.model.checkout.Cart;
import com.ecommerce.awf.model.checkout.Customer;
import com.ecommerce.awf.model.checkout.ProductItem;
import com.ecommerce.awf.model.commerce.Brand;
import com.ecommerce.awf.model.commerce.Product;
import com.ecommerce.awf.repository.checkout.CartRepository;
import com.ecommerce.awf.repository.checkout.CustomerRepository;
import com.ecommerce.awf.repository.checkout.ProductItemRepository;
import com.ecommerce.awf.repository.commerce.BrandRepository;
import com.ecommerce.awf.repository.commerce.ProductRepository;
import com.ecommerce.awf.service.rest.CartServices;
import com.ecommerce.awf.service.rest.ProductServices;
import com.ecommerce.awf.util.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CartServicesImpl implements CartServices {

    private final CartRepository cartRepository;
    private final ProductItemRepository productItemRepository;
    private final CustomerRepository customerRepository;
    private final String CARD_COOKIE_ID = "CartID";

    @Autowired
    public CartServicesImpl(CartRepository cartRepository, ProductItemRepository productItemRepository, CustomerRepository customerRepository){
        this.cartRepository = cartRepository;
        this.productItemRepository = productItemRepository;
        this.customerRepository = customerRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Cart createCart(Cart cart){
        return cartRepository.save(cart);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Cart updateCart(Cart cart){
        return cartRepository.save(cart);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProductItem createProductItem(ProductItem productItem){
        return productItemRepository.save(productItem);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProductItem updateProductItem(ProductItem productItem){
        return productItemRepository.save(productItem);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Cart findCartByCookieValue(String cookieValue){
      return cartRepository.findCartByCookie(cookieValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Cart findCartByPaymentIntentId(String paymentIntentId){
        return cartRepository.findCartByPaymentIntentId(paymentIntentId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Cart findCartByCookieValue(HttpServletRequest request){
        String requestCookie = CookieUtils.getCookieValue(request , CARD_COOKIE_ID);
        return cartRepository.findCartByCookie(requestCookie);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List <ProductItem>findProductItems(Cart cart){
        return productItemRepository.findProductItemsByCart(cart);
    }

    /**
     * {@inheritDoc}
     */
    public ProductItem findProductItem(Integer id){
        Optional<ProductItem> productItem =  productItemRepository.findById(id);
        return productItem.orElse(null);
    }

    /**
     * {@inheritDoc}
     */
    public void removeProductItem(Integer id){
        productItemRepository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Customer createCustomer(Customer customer){
        return customerRepository.save(customer);
    }

}
