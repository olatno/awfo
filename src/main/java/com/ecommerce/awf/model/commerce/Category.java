package com.ecommerce.awf.model.commerce;

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
public class Category {
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category", fetch= FetchType.EAGER)
    @JsonIgnore
    private Collection<ProductGroup> productGroups;

    @OneToOne
    @JoinColumn(name = "gallery_id",referencedColumnName = "id")
    private Gallery gallery;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
}
