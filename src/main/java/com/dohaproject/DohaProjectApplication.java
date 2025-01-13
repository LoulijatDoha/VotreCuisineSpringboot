package com.dohaproject;

import com.dohaproject.VotreCuisine.Dao.ICategoryRepository;
import com.dohaproject.VotreCuisine.Dao.IRecetteRepository;
import com.dohaproject.VotreCuisine.Dao.IUtilisateurRepository;
import com.dohaproject.VotreCuisine.Entities.Category;
import com.dohaproject.VotreCuisine.Entities.Recette;
import com.dohaproject.VotreCuisine.Entities.Utilisateur;
import com.dohaproject.VotreCuisine.Entities.enumer.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class DohaProjectApplication {

    public static void main(String[] args) {

        SpringApplication.run(DohaProjectApplication.class, args);
    }


//    @Bean
//    public CommandLineRunner startup(
//            IUtilisateurRepository userRepo,
//            IRecetteRepository recepeRepo,
//            ICategoryRepository categoryRepo
//    ) {
//
//        return args -> {
//            Utilisateur user1 = new Utilisateur("doha", "doha@example.com", "root", Role.ADMIN);
//            Utilisateur user2 = new Utilisateur("siham", "siham@example.com", "root1", Role.USER);
//            Utilisateur user3 = new Utilisateur("doaa", "doaa@example.com", "root2", Role.USER);
//            userRepo.save(user1);
//            userRepo.save(user2);
//            userRepo.save(user3);
//
//            Category category1 = new Category("Plat principal", "Plat principal");
//            Category category2 = new Category("Dessert", "Dessert");
//            Category category3 = new Category("Boisson", "Boisson");
//            Category category4 = new Category("Boisson", "Boisson");
//            categoryRepo.save(category4);
//            categoryRepo.save(category1);
//            categoryRepo.save(category2);
//            categoryRepo.save(category3);
//
//
//
//            Recette recette1 = new Recette("Plat principal", category1, "1h", List.of("Ingrédient 1", "Ingrédient 2"), "Etape 1-Etape 2");
//            Recette recette2 = new Recette("Dessert", category2, "2h", List.of("Ingrédient 1", "Ingrédient 2"), "Etape 1-Etape 2");
//            Recette recette3 = new Recette("Boisson", category1, "3h", List.of("Ingrédient 1", "Ingrédient 2"), "Etape 1-Etape 2");
//            Recette recette4 = new Recette("Boisson", category1, "3h", List.of("Ingrédient 1", "Ingrédient 2"), "Etape 1-Etape 2");
//            Recette recette5 = new Recette("Boisson", category1, "3h", List.of("Ingrédient 1", "Ingrédient 2"), "Etape 1-Etape 2");
//            Recette recette6 = new Recette("Boisson", category1, "3h", List.of("Ingrédient 1", "Ingrédient 2"), "Etape 1-Etape 2");
//            recepeRepo.save(recette5);
//            recepeRepo.save(recette6);
//            recepeRepo.save(recette4);
//            recepeRepo.save(recette1);
//            recepeRepo.save(recette2);
//            recepeRepo.save(recette3);
//            System.out.println("----------hellp---------");
//            System.out.println(category1.toString());
//            System.out.println(recepeRepo.findByCategorie_Name("Plat principal"));
//            for (Recette recette : recepeRepo.findByCategorie(category1)) {
//                recette.toString();
//            }
////            System.out.println();
//
////            Category category1 = new Category("Plat principal", "Plat principal");
////            Category category2 = new Category("Dessert", "Dessert");
////            Category category3 = new Category("Boisson", "Boisson");
////            categoryRepo.save(category1);
////            categoryRepo.save(category2);
////            categoryRepo.save(category3);
//
//
//        };
//
//    }

}
