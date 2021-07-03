package com.ecommerce.awf.model.checkout;

import com.ecommerce.awf.model.commerce.ProductCode;
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

public class CustomerOrder {

    private LocalDate created;
    private String orderConfirmation;
    private String cardType;
    @OneToOne
    @JoinColumn(name = "cart_id", referencedColumnName="id")
    private Cart cart;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
}
