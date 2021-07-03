package com.ecommerce.awf.enums;

public enum DiscountType {
    PERCENTAGE("PERCENTAGE"), FLAT("FLAT");

    private String discountType;


    DiscountType(String discountType){
        this.discountType = discountType;
    }
    public String getDiscountType() {
        return discountType;
    }

}
