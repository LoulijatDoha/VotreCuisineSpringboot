package ma.ensab.VotreCuisine.Dao;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import ma.ensab.VotreCuisine.Entities.*;


public interface UtilisateurRepository  extends JpaRepository<Utilisateur, Integer>{
	Optional<Utilisateur> findByUsernameAndPasswordAndEmail(String username, String password, String email);
    Optional<Utilisateur> findByUsername(String username);  // Pour vérifier si un utilisateur existe déjà
    Optional<Utilisateur> findByEmail(String email);

}
