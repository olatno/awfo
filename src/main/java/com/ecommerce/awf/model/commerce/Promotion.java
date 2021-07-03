package com.ecommerce.awf.model.commerce;

import com.ecommerce.awf.model.admin.PromotionType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Promotion {
    private String name;
    private String description;
    private LocalDate created;
    @OneToOne
    private PromotionType promotionType;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
}
