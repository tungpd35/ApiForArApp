package com.example.webapi.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class ClothesImage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String imageUrl;
    @ManyToOne
    @JoinColumn(name = "clothes_id")
    @JsonBackReference
    private Clothes clothes;

    public ClothesImage(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public ClothesImage() {
    }
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Clothes getClothes() {
        return clothes;
    }

    public void setClothes(Clothes clothes) {
        this.clothes = clothes;
    }

}
