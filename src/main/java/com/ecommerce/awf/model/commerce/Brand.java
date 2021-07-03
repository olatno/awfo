package com.ecommerce.awf.model.commerce;

import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;


import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
//@Table(uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Brand {

    private String name;
    private String logoPath;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "brand", fetch= FetchType.EAGER)
    @JsonIgnore
    private Collection<ProductCode> productCodes;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
}
