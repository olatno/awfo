//package com.ecommerce.awf.builder;
//
//import com.ecommerce.awf.model.admin.PromotionType;
//import com.ecommerce.awf.model.commerce.Promotion;
//
//import java.time.LocalDate;
//
//public class PromotionBuilder {
//    private String name;
//    private String description;
//    private LocalDate created;
//    private PromotionType promotionType;
//
//    public PromotionBuilder setName(String name) {
//        this.name = name;
//        return this;
//    }
//
//    public PromotionBuilder setDescription(String description) {
//        this.description = description;
//        return this;
//    }
//
//    public PromotionBuilder setCreated(LocalDate created) {
//        this.created = created;
//        return this;
//    }
//
//    public PromotionBuilder setPromotionType(PromotionType promotionType) {
//        this.promotionType = promotionType;
//        return this;
//    }
//
//    public Promotion createPromotion() {
//        return new Promotion(name, description, created, promotionType);
//    }
//}