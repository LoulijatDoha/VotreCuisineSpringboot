package com.dohaproject.VotreCuisine.Controllers;


import java.util.List;

import com.dohaproject.VotreCuisine.Dao.*;
import com.dohaproject.VotreCuisine.Entities.Category;
import com.dohaproject.VotreCuisine.Entities.Recette;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IRecetteRepository recipeRepository;

    @Autowired
    private IUtilisateurRepository userRepository;

    @GetMapping("/console")
    public String dashboard(Model model) {
        model.addAttribute("recettes", recipeRepository.findAll());
        model.addAttribute("utilisateurs", userRepository.findAll());
        return "admin/console"; // Correspond au fichier admin_dashboard.html
    }

    @PostMapping("/recettes/add")
    public String addArticle(@RequestParam String titre, @RequestParam Category categorie, @RequestParam String tempsPreparation, @RequestParam List<String> ingredients,
                             @RequestParam String etapesPreparation) {
        Recette recette = new Recette(titre, categorie, tempsPreparation, ingredients, etapesPreparation);
        recipeRepository.save(recette);
        return "redirect:/admin/console";
    }

    @PostMapping("/recettes/delete/{id}")
    public String deleteArticle(@PathVariable Integer id) {
        recipeRepository.deleteById(id);
        return "redirect:/admin/console";
    }
    
    @PostMapping("/utilisateurs/delete/{id}")
    public String deleteUser(@PathVariable Integer id) {
        userRepository.deleteById(id);
        return "redirect:/aadmin/console";
    }

}
