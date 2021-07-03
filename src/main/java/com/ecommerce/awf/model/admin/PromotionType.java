package com.ecommerce.awf.model.admin;

import com.ecommerce.awf.enums.DiscountType;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class PromotionType {
    private int value;
    @Enumerated(EnumType.STRING)
    private DiscountType type;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
}
