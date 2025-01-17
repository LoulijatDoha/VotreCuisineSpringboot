package com.dohaproject.VotreCuisine.Controllers;

import com.dohaproject.VotreCuisine.Entities.Recette;
import com.dohaproject.VotreCuisine.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private RecetteService recetteService;


    // Affichage de la page d'accueil avec toutes les recettes triées par nombre de likes
    @GetMapping("/")
    public String homePage(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, Model model) {
        // Récupérer les recettes populaires triées par nombre de likes
        Page<Recette> recettesPage = recetteService.getPopularRecipes(page,size);
        model.addAttribute("recettes", recettesPage.getContent());
        model.addAttribute("currentPage",page);
        model.addAttribute("totalPages",recettesPage.getTotalPages());
        model.addAttribute("totalElements",recettesPage.getTotalElements());


        // Récupérer la liste des catégories pour la barre latérale
        //List<String> categories = recipeRepository.findDistinctCategories();
        //model.addAttribute("categories", categories);

        return "home"; // Page d'accueil
    }

    // Affichage des recettes pour une catégorie spécifique, triées par nombre de likes
    @GetMapping("/categories/{category}")
    public String filterByCategory(@PathVariable String category,@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, Model model) {
        // Récupérer les recettes de la catégorie choisie, triées par nombre de likes
        Page<Recette> recettesPage = recetteService.getRecipesByCategory(category,page,size);
        model.addAttribute("recettes", recettesPage.getContent());
        model.addAttribute("currentPage",page);
        model.addAttribute("totalPages",recettesPage.getTotalPages());
        model.addAttribute("totalElements",recettesPage.getTotalElements());
      //  Récupérer la liste des catégories pour la barre latérale
        //List<String> categories = recetteRepository.findDistinctCategories();
       // model.addAttribute("categories", categories);

        return "home"; // Page d'accueil filtrée par catégorie
    }

    // Afficher toutes les recettes (optionnellement, on pourrait l'utiliser dans un autre contexte)
    //   @GetMapping("/recettes")
    //   public List<Recette> getAllRecettes() {
    //        return recipeRepository.findAll(); // Retourne toutes les recettes sans tri
    //  }
}