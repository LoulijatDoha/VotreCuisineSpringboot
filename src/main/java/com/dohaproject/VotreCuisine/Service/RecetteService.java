package com.dohaproject.VotreCuisine.Service;

import com.dohaproject.VotreCuisine.Dao.ICategoryRepository;
import com.dohaproject.VotreCuisine.Dao.IRecetteRepository;
import com.dohaproject.VotreCuisine.Entities.Category;
import com.dohaproject.VotreCuisine.Entities.Recette;
import com.dohaproject.VotreCuisine.dto.RecetteDTO;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
public class RecetteService {

    @Autowired
    private IRecetteRepository recetteRepository; @Autowired private ICategoryRepository categoryRepository;

    public Recette updateRecette(int id, RecetteDTO recetteDTO) {
        Recette recette = recetteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recette non trouvée"));

        Category category = categoryRepository.findById(recetteDTO.categoryId())
                .orElseThrow(() -> new RuntimeException("Catégorie non trouvée"));

        recette.setTitre(recetteDTO.titre());
        recette.setCategorie(category);
        recette.setTempsPreparation(recetteDTO.tempsPreparation());
        recette.setIngredients(recetteDTO.ingredients());
        recette.setEtapesPreparation(recetteDTO.etapesPreparation());

        return recetteRepository.save(recette);
    }


    public Recette createRecette(RecetteDTO recetteDTO) {

    Category category = categoryRepository.findById(recetteDTO.categoryId())
                .orElseThrow(() -> new RuntimeException("Catégorie non trouvée"));

        Recette recette = new Recette(
                recetteDTO.titre(),
                category,
                recetteDTO.tempsPreparation(),
                recetteDTO.ingredients(),
                recetteDTO.etapesPreparation()
        );

        return recetteRepository.save(recette);}

    public List<Recette> findRecettesByCategory(int categoryId) {
        // Vérifier si la catégorie existe
        categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Catégorie non trouvée"));

        // Retourner la liste des recettes correspondant à la catégorie
        return recetteRepository.findByCategorieId(categoryId);
    }

}
