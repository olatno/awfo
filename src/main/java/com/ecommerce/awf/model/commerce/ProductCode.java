package com.ecommerce.awf.model.commerce;

//import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ProductCode {
    private String code;
    private String description;
    @ManyToOne
    @JoinColumn(name = "brand_id", referencedColumnName="id")
    private Brand brand;
    @ManyToOne
    @JoinColumn(name = "productGroup_id", referencedColumnName="id")
    private ProductGroup productGroup;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
}
