package com.example.webapi.models;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "clothes")
public class Clothes{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name ="style_id",nullable = false,referencedColumnName = "style_id")
    @JsonBackReference
    private Style style;
    private String name;
    @Column(length = 10000)
    private String description;
    private String model3d;
    @OneToMany(mappedBy = "clothes",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ClothesImage> otherImage;
    public Clothes(String name, String description, String model3d) {
        this.name = name;
        this.model3d = model3d;
        this.description = description;
    }

    public Clothes() {

    }

    @Override
    public String toString() {
        return "Clothes{" +
                "id=" + id +
                ", style=" + style +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }


    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel3d() {
        return model3d;
    }

    public void setModel3d(String model3d) {
        this.model3d = model3d;
    }

    public List<ClothesImage> getOtherImage() {
        return otherImage;
    }

    public void setOtherImage(List<ClothesImage> otherImage) {
        this.otherImage = otherImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
