package com.ecommerce.awf.model.commerce;

import com.ecommerce.awf.model.admin.Image;
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
public class Gallery {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gallery", fetch= FetchType.LAZY)
    private Collection<Image> images;
    private String name;
    private LocalDate created;
    private String description;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
}
