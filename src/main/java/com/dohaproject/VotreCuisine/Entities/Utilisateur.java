package com.dohaproject.VotreCuisine.Entities;

import com.dohaproject.VotreCuisine.Entities.enumer.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor // Lombok annotation pour générer les getters et setters
@Entity
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false, unique = true)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;














    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

	public Utilisateur(String username, String email, String password, Role role) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
        this.role = role;
	}



	public Utilisateur() {
		super();
	}



	@ManyToMany(mappedBy = "utilisateursLikes")
    private List<Recette> recettesLikees;

    @ManyToMany(mappedBy = "utilisateursVisites")
    private List<Recette> recettesVisitees;





















    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Recette> getRecettesLikees() {
        return recettesLikees;
    }

    public void setRecettesLikees(List<Recette> recettesLikees) {
        this.recettesLikees = recettesLikees;
    }

    public List<Recette> getRecettesVisitees() {
        return recettesVisitees;
    }

    public void setRecettesVisitees(List<Recette> recettesVisitees) {
        this.recettesVisitees = recettesVisitees;
    }
    
    
    
}
