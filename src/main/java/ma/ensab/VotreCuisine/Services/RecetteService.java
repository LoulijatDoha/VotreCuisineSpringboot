package ma.ensab.VotreCuisine.Services;

import ma.ensab.VotreCuisine.Dao.RecetteRepository;
import ma.ensab.VotreCuisine.Entities.Recette;
import ma.ensab.VotreCuisine.Entities.Utilisateur;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// Marks this class as a service, containing business logic
@Service
public class RecetteService {

    // Automatically injects the RecetteRepository dependency
    @Autowired
    private RecetteRepository recetteRepository;


    /**
     * Searches for recipes by query and optional category.
     *
     * @param query The search query (required).
     * @param category The category to filter by (optional).
     * @return A list of recipes matching the criteria.
     */
    public List<Recette> searchRecipes(String query, String categorie) {
         if (categorie != null && !categorie.trim().isEmpty()) {
             // Search by both name and category
             return recetteRepository.findByTitreContainingIgnoreCaseAndCategorieContainingIgnoreCase(query, categorie);
         } else {
             // Search by name
             return recetteRepository.findByTitreContainingIgnoreCase(query);
         }
    }

    public Page<Recette> getPopularRecipes(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
       return (Page<Recette>) recetteRepository.findAllByOrderByLikesDesc(pageable);
    }


    public Page<Recette> getRecipesByCategory(String category, int page, int size) {
       Pageable pageable = PageRequest.of(page,size);
        return (Page<Recette>) recetteRepository.findByCategorieOrderByLikesDesc(category,pageable);
    }

    public void incrementLikes(int recetteId, Utilisateur utilisateur, HttpSession session){
       List<Integer> likedRecettes = getLikedRecettesFromSession(session);
      if (!likedRecettes.contains(recetteId)) {
          likedRecettes.add(recetteId);
          session.setAttribute("likedRecettes", likedRecettes);
          recetteRepository.incrementLikes(recetteId);
      }
    }

    public void decrementLikes(int recetteId, Utilisateur utilisateur, HttpSession session){
        List<Integer> likedRecettes = getLikedRecettesFromSession(session);
      if (likedRecettes.contains(recetteId)) {
            likedRecettes.remove(Integer.valueOf(recetteId));
            session.setAttribute("likedRecettes", likedRecettes);
            recetteRepository.decrementLikes(recetteId);
        }
    }

    private List<Integer> getLikedRecettesFromSession(HttpSession session) {
        @SuppressWarnings("unchecked")
        List<Integer> likedRecettes = (List<Integer>) session.getAttribute("likedRecettes");
         if(likedRecettes == null){
             likedRecettes = new ArrayList<>();
         }
        return likedRecettes;
    }


    public Recette getRecipeById(Integer id){
        return recetteRepository.findById((int) id).orElse(null);
    }
}