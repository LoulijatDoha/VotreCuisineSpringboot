package ma.ensab.VotreCuisine.Controllers;

import ma.ensab.VotreCuisine.Entities.*;
import ma.ensab.VotreCuisine.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import ma.ensab.VotreCuisine.Services.RecetteService;

import java.util.List;
import java.util.Optional;



@Controller
@RequestMapping("/utilisateurs")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RecetteService recetteService;



    // Affichage de la page de connexion
    @GetMapping("/login")
    public String loginPage() {
        return "login"; // Correspond au fichier templates/login.html
    }

    // Traitement du formulaire de connexion
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        @RequestParam String email,
                        @RequestParam String type,
                        Model model,
                        HttpSession session) {
        // Recherche de l'utilisateur dans la base de données
        Optional<Utilisateur> user = userService.findUserByUsernamePasswordEmail(username, password, email);

        if (user.isPresent()) {
            // Stockage de l'utilisateur dans la session
            session.setAttribute("Utilisateur", user.get());

            // Redirection en fonction du rôle de l'utilisateur
            if ("admin".equalsIgnoreCase(type)) {
                return "redirect:/admin/console";
            } else if ("normalUser".equalsIgnoreCase(type)) {
                return "redirect:/";
            } else {
                model.addAttribute("error", "Type d'utilisateur invalide");
                return "signup"; // Retourner au formulaire avec un message d'erreur
            }

        }

        // Gestion du cas où les identifiants sont incorrects
        model.addAttribute("error", "Identifiant ou mot de passe invalide");
        return "login"; // Retour à la page de connexion avec un message d'erreur
    }

    // Affichage du formulaire d'inscription
    @GetMapping("/signup")
    public String signupPage() {
        return "signup"; // Correspond au fichier templates/signup.html
    }

    // Traitement du formulaire d'inscription
    @PostMapping("/signup")
    public String signup(@RequestParam String username,
                         @RequestParam String email,
                         @RequestParam String password,
                         @RequestParam String type,
                         Model model) {
        // Vérification si l'utilisateur existe déjà
        if (userService.doesUsernameExist(username)) {
            model.addAttribute("error", "Utilisateur déjà existant");
            return "signup"; // Retourner au formulaire avec un message d'erreur
        } else {
            // Création de l'utilisateur en fonction du type
            Utilisateur newUser;
            if ("admin".equalsIgnoreCase(type)) {
                newUser = new Admin(username, email, password);
            } else if ("normalUser".equalsIgnoreCase(type)) {
                newUser = new utilisateurNormal(username, email, password);
            } else {
                model.addAttribute("error", "Type d'utilisateur invalide");
                return "signup"; // Retourner au formulaire avec un message d'erreur
            }
            // Sauvegarde de l'utilisateur dans la base de données
            userService.save(newUser);
            return "redirect:/utilisateurs/login"; // Rediriger vers la page de connexion après inscription réussie
        }
    }


    // Affichage des recettes aimées d'un utilisateur
    @GetMapping("/myRecetteslikees")
    public String viewMyRecetteslikees(HttpSession session, Model model) {
        Utilisateur user = (Utilisateur) session.getAttribute("Utilisateur");
        if (user == null) {
            return "redirect:/utilisateurs/login"; // Rediriger si l'utilisateur n'est pas connecté
        }
        List<Recette> likees = (List<Recette>) user.getRecettesLikees();
        model.addAttribute("recetteslikees", likees);
        return "user_recetteslikees";
    }

    //  Récupérer les recettes visitées de l'utilisateur
    @GetMapping("/myRecettesVisitees")
    public String viewMyRecettesvisitees(HttpSession session, Model model) {
        Utilisateur user = (Utilisateur) session.getAttribute("Utilisateur");
        if (user == null) {
            return "redirect:/utilisateurs/login"; // Rediriger si l'utilisateur n'est pas connecté
        }
        List<Recette> visites = (List<Recette>) user.getRecettesVisitees();
        model.addAttribute("recettesvisites", visites);
        return "user_recettesvisites";
    }

    // Affichage du profil utilisateur
    @GetMapping("/profile")
    public String viewProfile(HttpSession session, Model model) {
        Utilisateur user = (Utilisateur) session.getAttribute("Utilisateur");
        if (user == null) {
            return "redirect:/utilisateurs/login"; // Rediriger vers la page de connexion si l'utilisateur n'est pas connecté
        }
        model.addAttribute("utilisateur", user);
        return "profile"; // Afficher la vue du profil
    }

    // Mise à jour du profil utilisateur
    @PostMapping("/updateProfile")
    public String updateProfile(@RequestParam String username,
                                @RequestParam String email,
                                @RequestParam String password,
                                HttpSession session,
                                Model model) {
        Utilisateur user = (Utilisateur) session.getAttribute("Utilisateur");
        if (user == null) {
            return "redirect:/"; // Rediriger si l'utilisateur n'est pas connecté
        }
        // Mise à jour des informations de l'utilisateur
        user.setUsername(username);
        user.setUsername(email);
        user.setPassword(password);  // Si vous souhaitez ajouter de la validation, comme hacher le mot de passe, vous pouvez le faire ici.
        // Sauvegarder les modifications dans la base de données
        userService.save(user);
        // Ajouter un message de succès
        model.addAttribute("success", "Profil mis à jour avec succès !");
        return "redirect:/utilisateurs/profile"; // Rediriger vers le profil mis à jour
    }


    @PostMapping("/user/delete")
    public String deleteUser(HttpSession session) {
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("Utilisateur");
        if (utilisateur != null) {
           userService.delete(utilisateur);
            session.invalidate();
        }
        return "redirect:/";
    }


    // Redirection vers la page de recette (si on a dans une recette ou page home si on a déjà dans l'home
    // Déconnexion de l'utilisateur
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();  // Invalider la session pour déconnecter l'utilisateur
        return "redirect:/"; // Rediriger vers la page de connexion
    }
}