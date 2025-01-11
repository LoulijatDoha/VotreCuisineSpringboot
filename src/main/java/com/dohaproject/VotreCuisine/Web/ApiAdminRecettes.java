package com.dohaproject.VotreCuisine.Web;


import com.dohaproject.VotreCuisine.Dao.ICategoryRepository;
import com.dohaproject.VotreCuisine.Dao.IRecetteRepository;
import com.dohaproject.VotreCuisine.Entities.Recette;
import com.dohaproject.VotreCuisine.Service.RecetteService;
import com.dohaproject.VotreCuisine.dto.RecetteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class ApiAdminRecettes {

    @Autowired private IRecetteRepository recetteRepository; @Autowired private ICategoryRepository categoryRepository;
    @Autowired private RecetteService recetteService;


    public ApiAdminRecettes(IRecetteRepository recetteRepository,
                            ICategoryRepository categoryRepository) {
        this.recetteRepository = recetteRepository;
        this.categoryRepository = categoryRepository;
    }

    // Afficher toutes les recettes
    @GetMapping
    public ResponseEntity<List<Recette>> getAllRecettes() {
        List<Recette> recettes = recetteRepository.findAll();
        return ResponseEntity.ok(recettes);
    }

    // Récupérer une recette par ID
    @GetMapping("/{id}")
    public ResponseEntity<Recette> getRecetteById(@PathVariable int id) {
        Recette recette = recetteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recette non trouvée"));
        return ResponseEntity.ok(recette);
    }


    // Supprimer une recette
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRecette(@PathVariable int id) {
        try {
            recetteRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Mettre à jour une recette
    @PutMapping("/{id}")
    public ResponseEntity<Recette> updateRecette(@PathVariable int id, @RequestBody RecetteDTO recetteDTO) {
        try {
            Recette recetteModifiee = recetteService.updateRecette(id, recetteDTO);
            return ResponseEntity.ok(recetteModifiee);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    // Ajouter une nouvelle recette
    @PostMapping
    public ResponseEntity<Recette> createRecette(@RequestBody RecetteDTO recetteDTO) {
        try {
            Recette nouvellRecette = recetteService.createRecette(recetteDTO);
            return new ResponseEntity<>(nouvellRecette, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


}
