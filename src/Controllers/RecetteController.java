package com.dohaproject.VotreCuisine.Controllers;

import com.dohaproject.VotreCuisine.Entities.Recette;
import com.dohaproject.VotreCuisine.Entities.Utilisateur;
import com.dohaproject.VotreCuisine.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/recette")
public class RecetteController {

    @Autowired
    private RecetteService recetteService;

    // Endpoint pour récupérer les détails d'une recette
    @GetMapping("/{id}")
    public ResponseEntity<?> getRecetteDetails(@PathVariable Integer id) {
         Recette recette = recetteService.getRecipeById(id);
        if (recette!=null) {
            return ResponseEntity.ok(recette);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recette non trouvée");
        }
    }


    // Endpoint pour liker une recette
    @PostMapping("/{id}/like")
    public ResponseEntity<?> likeRecette(@PathVariable Integer id, HttpSession session) {
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("Utilisateur");
        if (utilisateur == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Non connecté");
        }
        try {
            List<Integer> likedRecettes = (List<Integer>) session.getAttribute("likedRecettes");
             if(likedRecettes != null && likedRecettes.contains(id)){
                 recetteService.decrementLikes(id, utilisateur,session);
             } else {
                 recetteService.incrementLikes(id, utilisateur,session);
             }
             return ResponseEntity.ok("Like mis à jour");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne");
        }
    }
}