package com.dohaproject.VotreCuisine.Service;

import com.dohaproject.VotreCuisine.Dao.ICategoryRepository;
import com.dohaproject.VotreCuisine.Dao.IRecetteRepository;
import com.dohaproject.VotreCuisine.Entities.Category;
import com.dohaproject.VotreCuisine.dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CategoryService {

    @Autowired
    private IRecetteRepository recetteRepository; @Autowired private ICategoryRepository categoryRepository;
    public Category createCategory(CategoryDTO categoryDTO) {
        Category category = new Category(categoryDTO.name(), categoryDTO.desc());
        return categoryRepository.save(category);
    }

    public Category updateCategory(int id, CategoryDTO categoryDTO) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Catégorie non trouvée"));

        category.setName(categoryDTO.name());
        category.setDescription(categoryDTO.desc());
        return categoryRepository.save(category);
    }
}
