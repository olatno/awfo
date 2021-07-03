package com.ecommerce.awf.model.admin;

import com.ecommerce.awf.enums.ProductStatus;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Country {
    private String name;
    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
}
