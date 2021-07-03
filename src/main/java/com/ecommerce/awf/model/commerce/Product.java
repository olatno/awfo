package com.ecommerce.awf.model.commerce;

import com.ecommerce.awf.model.admin.Country;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Product {
    private String name;
    private String description;
    private LocalDate created;

    @OneToOne
    @JoinColumn(name = "gallery_id",referencedColumnName = "id")
    private Gallery gallery;

    @OneToOne
    @JoinColumn(name = "country_id",referencedColumnName = "id")
    private Country country;

    @OneToOne
    @JoinColumn(name = "product_code_id", referencedColumnName="id")
    private ProductCode productCode;

    @ManyToOne
    @JoinColumn(name = "product_group_id", referencedColumnName="id")
    private ProductGroup productGroup;

    @OneToOne
    @JoinColumn(name = "product_weight_id", referencedColumnName="id")
    private ProductWeight productWeight;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
}
