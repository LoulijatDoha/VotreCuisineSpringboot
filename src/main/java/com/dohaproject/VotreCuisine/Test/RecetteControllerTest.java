package com.dohaproject.VotreCuisine.Test;/*
 * package ma.ensab.VotreCuisine.Test;
 * 
 * import ma.ensab.VotreCuisine.Controllers.*; // Missing import import
 * ma.ensab.VotreCuisine.Dao.*; import ma.ensab.VotreCuisine.Entities.*; import
 * jakarta.servlet.http.HttpSession;
 * 
 * import org.junit.jupiter.api.BeforeEach; import org.junit.jupiter.api.Test;
 * import org.junit.jupiter.api.extension.ExtendWith; import
 * org.mockito.InjectMocks; import org.mockito.Mock; import
 * org.mockito.junit.jupiter.MockitoExtension; import
 * jakarta.servlet.http.HttpSession; import static org.mockito.Mockito.*;
 * 
 * import org.springframework.http.HttpStatus; import
 * org.springframework.http.ResponseEntity; import java.util.ArrayList; import
 * java.util.List; import java.util.Optional;
 * 
 * 
 * @ExtendWith(MockitoExtension.class) public class RecetteControllerTest {
 * 
 * @Mock private IRecetteRepository recetteRepository;
 * 
 * @Mock private HttpSession session;
 * 
 * @InjectMocks private RecetteController recetteController;
 * 
 * private Recette recette; private Utilisateur utilisateur;
 * 
 * @BeforeEach void setup(){ recette = new Recette(); recette.setId(12);
 * utilisateur = new Utilisateur(); }
 * 
 * @Test void testGetRecetteDetails_RecipeExists() {
 * when(recetteRepository.findById(12)).thenReturn(Optional.of(recette));
 * ResponseEntity<?> response = recetteController.getRecetteDetails(12);
 * assertEquals(HttpStatus.OK, response.getStatusCode()); assertEquals(recette,
 * response.getBody()); verify(recetteRepository, times(1)).findById(12); }
 * 
 * @Test void testGetRecetteDetails_RecipeNotFound() {
 * when(recetteRepository.findById(1)).thenReturn(Optional.empty());
 * ResponseEntity<?> response = recetteController.getRecetteDetails(12);
 * assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
 * assertEquals("Recette non trouvée", response.getBody());
 * verify(recetteRepository, times(1)).findById(12); }
 * 
 * @Test void testLikeRecette_UserNotLoggedIn() {
 * when(session.getAttribute("Utilisateur")).thenReturn(null); ResponseEntity<?>
 * response = recetteController.likeRecette(12, session);
 * assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
 * assertEquals("Non connecté", response.getBody()); }
 * 
 * @Test void testLikeRecette_LikeSuccess() {
 * when(session.getAttribute("Utilisateur")).thenReturn(utilisateur);
 * when(recetteRepository.findById(12)).thenReturn(Optional.of(recette));
 * ResponseEntity<?> response = recetteController.likeRecette(12,session);
 * assertEquals(HttpStatus.OK, response.getStatusCode());
 * assertEquals("Like mis à jour", response.getBody());
 * verify(recetteRepository, times(1)).incrementLikes(12); }
 * 
 * @Test void testLikeRecette_UnlikeSuccess() { List<Long> likedRecettes = new
 * ArrayList<>(); likedRecettes.add(1L);
 * when(session.getAttribute("Utilisateur")).thenReturn(utilisateur);
 * when(recetteRepository.findById(12)).thenReturn(Optional.of(recette));
 * when(session.getAttribute("likedRecettes")).thenReturn(likedRecettes);
 * 
 * ResponseEntity<?> response = recetteController.likeRecette(12,session);
 * assertEquals(HttpStatus.OK, response.getStatusCode());
 * assertEquals("Like mis à jour", response.getBody());
 * verify(recetteRepository, times(1)).decrementLikes(12); }
 * 
 * }
 */