package com.example.webapi.models;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.websocket.ClientEndpoint;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Table(name = "style")
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "styleId")
public class Style {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "style_id")
    private Long styleId;

    @OneToMany(mappedBy = "style",cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Clothes> clothes;
    private String styleName;
    @Column(length = 1000)
    private String styleDescription;
    private String image;


    public Style(String styleName, String styleDescription,String image) {
        this.styleName = styleName;
        this.styleDescription = styleDescription;
        this.image = image;
    }
    public Style() {}


    public long getStyleId() {
        return styleId;
    }

    public void setStyleId(long styleId) {
        this.styleId = styleId;
    }

    public String getStyleName() {
        return styleName;
    }

    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

    public String getStyleDescription() {
        return styleDescription;
    }

    public void setStyleDescription(String styleDescription) {
        this.styleDescription = styleDescription;
    }

    public Set<Clothes> getClothes() {
        return clothes;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setClothes(Set<Clothes> clothes) {
        this.clothes = clothes;
    }
}
