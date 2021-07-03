package com.ecommerce.awf.util;

import com.ecommerce.awf.util.address.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerParameters {
    private String description;
    private Address address;
}
