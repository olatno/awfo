//package com.ecommerce.awf.builder;
//
//import com.ecommerce.awf.model.admin.Image;
//import com.ecommerce.awf.model.commerce.Gallery;
//
//import java.time.LocalDate;
//
//public class ImageBuilder {
//    private Gallery gallery;
//    private String name;
//    private String description;
//    private String path;
//    private LocalDate created;
//
//    public ImageBuilder setGallery(Gallery gallery) {
//        this.gallery = gallery;
//        return this;
//    }
//
//    public ImageBuilder setName(String name) {
//        this.name = name;
//        return this;
//    }
//
//    public ImageBuilder setDescription(String description) {
//        this.description = description;
//        return this;
//    }
//
//    public ImageBuilder setPath(String path) {
//        this.path = path;
//        return this;
//    }
//
//    public ImageBuilder setCreated(LocalDate created) {
//        this.created = created;
//        return this;
//    }
//
//    public Image createImage() {
//        return new Image(gallery, name, description, path, created);
//    }
//}