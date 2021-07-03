package com.ecommerce.awf.util.address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressCommon {
    private String city;
    private String country;
    private String lineOne;
    private String lineTwo;
    private String postalCode;
    private String state;
    private String name;
    private String phone;
    private String email;
}
