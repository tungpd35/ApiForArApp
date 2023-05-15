package com.example.webapi.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "clothes"
)
public class Clothes {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;
    @ManyToOne
    @JoinColumn(
            name = "style_id",
            nullable = false,
            referencedColumnName = "style_id"
    )
    @JsonBackReference
    private Style style;
    private String name;
    @Column(
            length = 10000
    )
    private String description;
    private String model3d;
    @OneToMany(
            mappedBy = "clothes",
            cascade = {CascadeType.ALL}
    )
    @JsonManagedReference
    private List<ClothesImage> image = new ArrayList();

    public Clothes(String name, String description, String model3d) {
        this.name = name;
        this.model3d = model3d;
        this.description = description;
    }

    public Clothes(String name, String description, String model3d, List<ClothesImage> images) {
        this.name = name;
        this.model3d = model3d;
        this.description = description;
        this.image = images;
    }

    public Clothes(String name, String description, String model3d, String image) {
        this.name = name;
        this.model3d = model3d;
        this.description = description;
        ClothesImage clothesImage = new ClothesImage(image);
        this.image.add(clothesImage);
    }

    public Clothes() {
    }

    public String toString() {
        return "Clothes{id=" + this.id + ", style=" + this.style + ", name='" + this.name + "', description='" + this.description + "'}";
    }

    public Style getStyle() {
        return this.style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel3d() {
        return this.model3d;
    }

    public void setModel3d(String model3d) {
        this.model3d = model3d;
    }

    public List<ClothesImage> getImage() {
        return this.image;
    }

    public void setImage(List<ClothesImage> image) {
        this.image = image;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
