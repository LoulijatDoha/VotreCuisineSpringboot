package com.dohaproject.VotreCuisine.Services;

import com.dohaproject.VotreCuisine.Dao.IUtilisateurRepository;
import com.dohaproject.VotreCuisine.Entities.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private IUtilisateurRepository userRepository;


    public Optional<Utilisateur> findUserByUsernamePasswordEmail(String username, String password, String email) {
       return userRepository.findByUsernameAndPasswordAndEmail(username,password,email);
    }

    public boolean doesUsernameExist(String username){
        return userRepository.findByUsername(username).isPresent();
    }

    public Optional<Utilisateur> findUserById(Integer id){
       return userRepository.findById((int)id);
    }
    public Utilisateur save(Utilisateur utilisateur){
        return userRepository.save(utilisateur);
    }
    public void delete(Utilisateur utilisateur){
         userRepository.delete(utilisateur);
    }

    public List<Utilisateur> getAllUsers(){
        return userRepository.findAll();
    }
}