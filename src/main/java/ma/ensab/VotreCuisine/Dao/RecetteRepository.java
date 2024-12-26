package ma.ensab.VotreCuisine.Dao;


import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.ensab.VotreCuisine.Entities.*;

public interface RecetteRepository  extends JpaRepository<Recette, Integer>{
	
	// You can add custom query methods here if needed
    List<Recette> findByTitreContainingIgnoreCase(String titre);

    List<Recette> findByTitreContainingIgnoreCaseAndCategorieContainingIgnoreCase(String titre, String categorie);

	// Récupérer les recettes triées par nombre de likes
    List<Recette> findAllByOrderByLikesDesc(Pageable pageable);

    // Récupérer les recettes d'une catégorie spécifique, triées par nombre de likes
    List<Recette> findByCategorieOrderByLikesDesc(String categorie, Pageable pageable);

    // Récupérer toutes les catégories distinctes
    @Query("SELECT DISTINCT r.categorie FROM Recette r")
    List<String> findDistinctCategories();
    
    
    @Modifying
    @Query("UPDATE Recette r SET r.likes = r.likes + 1 WHERE r.id = :recetteId")
    void incrementLikes(@Param("recetteId") int recetteId);

    @Modifying
    @Query("UPDATE Recette r SET r.likes = r.likes - 1 WHERE r.id = :recetteId")
    void decrementLikes(@Param("recetteId") int recetteId);
    
    @Query("SELECT r FROM Recette r WHERE " +
            "(:query IS NULL OR LOWER(r.titre) LIKE LOWER(CONCAT('%', :query, '%'))) AND " +
            "(:categorie IS NULL OR LOWER(r.categorie) LIKE LOWER(CONCAT('%', :categorie, '%')))")
     List<Recette> searchRecipes(@Param("query") String query, @Param("categorie") String category);
    
 

}
