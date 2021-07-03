//package com.ecommerce.awf.builder;
//
//import com.ecommerce.awf.model.commerce.Gallery;
//
//import java.time.LocalDate;
//
//public class GalleryBuilder {
//    private String name;
//    private LocalDate created;
//    private String description;
//
//    public GalleryBuilder setName(String name) {
//        this.name = name;
//        return this;
//    }
//
//    public GalleryBuilder setCreated(LocalDate created) {
//        this.created = created;
//        return this;
//    }
//
//    public GalleryBuilder setDescription(String description) {
//        this.description = description;
//        return this;
//    }
//
//    public Gallery createGallery() {
//        return new Gallery(name, created, description);
//    }
//}