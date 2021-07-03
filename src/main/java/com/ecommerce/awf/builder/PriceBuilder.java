//package com.ecommerce.awf.builder;
//
//import com.ecommerce.awf.model.commerce.Price;
//import com.ecommerce.awf.model.commerce.Promotion;
//
//import java.math.BigDecimal;
//
//public class PriceBuilder {
//    private BigDecimal value;
//    private Promotion promotion;
//
//    public PriceBuilder setValue(BigDecimal value) {
//        this.value = value;
//        return this;
//    }
//
//    public PriceBuilder setPromotion(Promotion promotion) {
//        this.promotion = promotion;
//        return this;
//    }
//
//    public Price createPrice() {
//        return new Price(value, promotion);
//    }
//}