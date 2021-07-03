package com.ecommerce.awf.model.checkout;

import com.ecommerce.awf.model.commerce.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ProductItem {

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "cart_id", referencedColumnName="id")
    private Cart cart;

    private int quantity;

    private int weightListIndex;

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName="id")
    private Product product;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
}
