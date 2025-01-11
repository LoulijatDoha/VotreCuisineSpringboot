package com.dohaproject.VotreCuisine.Web;

import com.dohaproject.VotreCuisine.Dao.ICategoryRepository;
import com.dohaproject.VotreCuisine.Dao.IRecetteRepository;
import com.dohaproject.VotreCuisine.Entities.Category;
import com.dohaproject.VotreCuisine.Service.CategoryService;
import com.dohaproject.VotreCuisine.dto.CategoryDTO;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/api/admin/categories")
@NoArgsConstructor
public class ApiAdminCategory {
    @Autowired
    private IRecetteRepository recetteRepository; @Autowired private ICategoryRepository categoryRepository;
    @Autowired private CategoryService categoryService;


    // Récupérer une catégorie par ID
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable int id) {
        return categoryRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Supprimer une catégorie
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable int id) {
        try {
            categoryRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Créer une nouvelle catégorie
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody CategoryDTO categoryDTO) {
        Category category = categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    // Mettre à jour une catégorie
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable int id, @RequestBody CategoryDTO categoryDTO) {
        try {
            Category category = categoryService.updateCategory(id, categoryDTO);
            return ResponseEntity.ok(category);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }




}
