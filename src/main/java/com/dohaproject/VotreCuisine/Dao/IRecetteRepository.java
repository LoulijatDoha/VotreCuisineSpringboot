package com.dohaproject.VotreCuisine.Dao;


import java.util.List;

import com.dohaproject.VotreCuisine.Entities.Category;
import com.dohaproject.VotreCuisine.Entities.Recette;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IRecetteRepository extends JpaRepository<Recette, Integer>{

    //find recete by categorie
    List<Recette> findByCategorie_Name(String categorieNom);
    List<Recette> findByCategorie(Category category);
    List<Recette> findByCategorieId(int categoryId);


 

}
