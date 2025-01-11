package com.dohaproject.VotreCuisine.Web;


import com.dohaproject.VotreCuisine.Entities.Recette;
import com.dohaproject.VotreCuisine.Service.UserService.FavorisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/favoris")
public class ApiUser {

    @Autowired
    private FavorisService userService;

    @PostMapping("/{userId}/{recetteId}")
    public ResponseEntity<?> ajouterFavori(@PathVariable int userId, @PathVariable int recetteId) {
        System.out.println("helloaa!!!!");
        try {
            userService.ajouterAuxFavoris(userId, recetteId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{userId}/{recetteId}")
    public ResponseEntity<?> retirerFavori(@PathVariable int userId, @PathVariable int recetteId) {
        try {
            userService.retirerDesFavoris(userId, recetteId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Recette>> getFavoris(@PathVariable int userId) {
        try {
            List<Recette> favoris = userService.getFavoris(userId);
            return ResponseEntity.ok(favoris);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{userId}/{recetteId}")
    public ResponseEntity<Boolean> estFavori(@PathVariable int userId, @PathVariable int recetteId) {
        try {
            boolean estFavori = userService.estFavori(userId, recetteId);
            return ResponseEntity.ok(estFavori);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

