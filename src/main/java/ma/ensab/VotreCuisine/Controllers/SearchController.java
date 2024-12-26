package ma.ensab.VotreCuisine.Controllers;

import ma.ensab.VotreCuisine.Entities.Recette;
    import ma.ensab.VotreCuisine.Services.RecetteService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    // Marks this class as a REST controller, handling incoming web requests
    @RestController
    @RequestMapping("/search")  // Maps all requests with prefix "/search" to this controller
    public class SearchController {

        // Automatically injects the RecetteService dependency
        @Autowired
        private RecetteService recetteService;

        /**
         * Endpoint to search for recipes by query and optional category.
         *
         * @param query The search query (required).
         * @param category The category to filter by (optional).
         * @return  A ResponseEntity containing either the list of found recipes, or a not found message with 404 status.
         */




        @PostMapping
        public ResponseEntity<?> searchRecipes(@RequestParam String query, @RequestParam(required = false) String categorie) {
            // Basic validation for the query
            if (query == null || query.trim().isEmpty()) {
                return ResponseEntity.badRequest().body("The query cannot be empty.");
            }
            try {
                List<Recette> recettes = recetteService.searchRecipes(query, categorie);

                // If no recipes found
                if(recettes.isEmpty()){
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No recipes found matching your criteria.");
                }

                // Return the recipes if found with HTTP 200 OK
                return ResponseEntity.ok(recettes);
            } catch (Exception e) {
                // Log the exception
                e.printStackTrace();
                // Return an Internal Server Error with a message
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during the search.");
            }
        }
    }