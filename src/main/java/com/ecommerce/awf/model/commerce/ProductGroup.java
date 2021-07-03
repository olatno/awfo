package com.ecommerce.awf.model.commerce;

//import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ProductGroup {
    private String name;
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName="id")
    private Category category;
    @OneToOne
    @JoinColumn(name = "gallery_id",referencedColumnName = "id")
    private Gallery gallery;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productGroup", fetch= FetchType.EAGER)
    @JsonIgnore
    private Collection<Product> products;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
}
