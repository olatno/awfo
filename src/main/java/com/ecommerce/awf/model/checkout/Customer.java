package com.ecommerce.awf.model.checkout;

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
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @OneToMany(mappedBy = "customer", fetch= FetchType.EAGER)
    @JsonIgnore
    private Collection<Cart> carts;
    private String stripeId;
    private String email;
    private String password;
}
