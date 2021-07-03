package com.ecommerce.awf.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailParameters {
    private String emailContent;
    private String emailTo;
    private String orderConfirmation;
}
