package com.ecommerce.awf.model.commerce;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Price {
    private BigDecimal value;
    @OneToOne
    private Promotion promotion;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
}
