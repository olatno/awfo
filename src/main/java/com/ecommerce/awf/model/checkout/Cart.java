package com.ecommerce.awf.model.checkout;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Cart {
    @OneToMany(mappedBy = "cart", fetch= FetchType.EAGER)
    private Collection<ProductItem> productItems;
    private String cookie;
    private LocalDate created;
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName="id")
    private Customer customer;
    private String paymentIntentId;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
}
