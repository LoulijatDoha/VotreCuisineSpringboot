package com.dohaproject.VotreCuisine.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Table(name = "category")
@Data @AllArgsConstructor
@ToString(exclude = {"recettes"})
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;



//    @OneToMany(mappedBy = "categorie", fetch = FetchType.LAZY)
    @OneToMany(mappedBy = "categorie", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Recette> recettes; // List of recipes in this category>




    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Category() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Recette> getRecettes() {
        return recettes;
    }

    public void setRecettes(List<Recette> recettes) {
        this.recettes = recettes;
    }
}
