package com.ecommerce.awf.model.admin;

import com.ecommerce.awf.model.commerce.Gallery;
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
public class Image {

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "gallery_id", referencedColumnName="id")
    private Gallery gallery;
    private String name;
    private String description;
    private String path;
    private LocalDate created;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
}
