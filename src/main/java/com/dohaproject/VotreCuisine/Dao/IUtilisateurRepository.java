package com.dohaproject.VotreCuisine.Dao;


import java.util.Optional;

import com.dohaproject.VotreCuisine.Entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IUtilisateurRepository extends JpaRepository<Utilisateur, Integer>{
	Optional<Utilisateur> findByUsernameAndPasswordAndEmail(String username, String password, String email);
    Optional<Utilisateur> findByUsername(String username);  // Pour vérifier si un utilisateur existe déjà
    Optional<Utilisateur> findByEmail(String email);

}
