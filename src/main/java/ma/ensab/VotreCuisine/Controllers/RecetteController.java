package ma.ensab.VotreCuisine.Controllers;

import ma.ensab.VotreCuisine.Entities.*;
import ma.ensab.VotreCuisine.Services.RecetteService;
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