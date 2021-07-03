//package com.ecommerce.awf.builder;
//
//import com.ecommerce.awf.enums.DiscountType;
//import com.ecommerce.awf.model.admin.PromotionType;
//
//public class PromotionTypeBuilder {
//    private int value;
//    private DiscountType type;
//
//    public PromotionTypeBuilder setValue(int value) {
//        this.value = value;
//        return this;
//    }
//
//    public PromotionTypeBuilder setType(DiscountType type) {
//        this.type = type;
//        return this;
//    }
//
//    public PromotionType createPromotionType() {
//        return new PromotionType(value, type);
//    }
//}