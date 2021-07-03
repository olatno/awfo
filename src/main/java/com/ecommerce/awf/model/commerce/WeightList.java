package com.ecommerce.awf.model.commerce;

import com.ecommerce.awf.enums.ProductWeightBase;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class WeightList {

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "productWeight_id", referencedColumnName="id")
    private ProductWeight productWeight;
    @OneToOne
    @JoinColumn(name = "price_id", referencedColumnName="id")
    private Price price;

    private int value;
    @Enumerated(EnumType.STRING)
    private ProductWeightBase productWeightBase;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
}
