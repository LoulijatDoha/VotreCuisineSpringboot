package com.dohaproject.VotreCuisine.Service.UserService;

import com.dohaproject.VotreCuisine.Dao.IRecetteRepository;
import com.dohaproject.VotreCuisine.Dao.IUtilisateurRepository;
import com.dohaproject.VotreCuisine.Entities.Recette;
import com.dohaproject.VotreCuisine.Entities.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FavorisService {

    @Autowired
    private IRecetteRepository recetteRepository;

    @Autowired
    private IUtilisateurRepository utilisateurRepository;

    public FavorisService(IRecetteRepository recetteRepository, IUtilisateurRepository utilisateurRepository) {
        this.recetteRepository = recetteRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    public void ajouterAuxFavoris(int userId, int recetteId) {
        Utilisateur utilisateur = utilisateurRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        Recette recette = recetteRepository.findById(recetteId)
                .orElseThrow(() -> new RuntimeException("Recette non trouvée"));

        if (!recette.getUtilisateursLikes().contains(utilisateur)) {
            recette.getUtilisateursLikes().add(utilisateur);
            recette.setLikes(recette.getLikes() + 1);
            recetteRepository.save(recette);
        }
    }

    public void retirerDesFavoris(int userId, int recetteId) {
        Utilisateur utilisateur = utilisateurRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        Recette recette = recetteRepository.findById(recetteId)
                .orElseThrow(() -> new RuntimeException("Recette non trouvée"));

        if (recette.getUtilisateursLikes().contains(utilisateur)) {
            recette.getUtilisateursLikes().remove(utilisateur);
            recette.setLikes(recette.getLikes() - 1);
            recetteRepository.save(recette);
        }
    }

    public List<Recette> getFavoris(int userId) {
        Utilisateur utilisateur = utilisateurRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        return utilisateur.getRecettesLikees();
    }

    public boolean estFavori(int userId, int recetteId) {
        Utilisateur utilisateur = utilisateurRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        return utilisateur.getRecettesLikees().stream()
                .anyMatch(recette -> recette.getId() == recetteId);
    }
}
