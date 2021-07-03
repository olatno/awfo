package com.ecommerce.awf.model.commerce;

import com.ecommerce.awf.enums.ProductWeightBase;
import com.ecommerce.awf.model.admin.Image;
import com.ecommerce.awf.model.admin.PromotionType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ProductWeight {

    private String description;

    @OneToMany(mappedBy = "productWeight", fetch= FetchType.LAZY)
    private Collection<WeightList> weightList;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
}
