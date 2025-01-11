package com.dohaproject.VotreCuisine.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "recettes")

public class Recette {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String titre;









    @Column(name = "temps_preparation")
    private String tempsPreparation;

	@Column(name = "ingredients", columnDefinition = "TEXT")
	@ElementCollection(fetch = FetchType.EAGER)
    private List<String> ingredients;

    @Column(name = "etapes_preparation", columnDefinition = "TEXT")
    private String etapesPreparation;

    private int likes;


	//une table d'asscociation ---fAVORIS--- entre recette et utilisateur many to many cote base de donnee
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "Favoris",
        joinColumns = @JoinColumn(name = "recepe_id"),
        inverseJoinColumns = @JoinColumn(name = "User_id")
    )
	private List<Utilisateur> utilisateursLikes;






	//une table d'asscociation ---VISITED--- entre recette et utilisateur many to many cote base de donnee
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "recette_utilisateurs_visites",
        joinColumns = @JoinColumn(name = "recette_id"),
        inverseJoinColumns = @JoinColumn(name = "utilisateur_id")
    )
    private List<Utilisateur> utilisateursVisites;




    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_categorie")
	private Category categorie;


	@Override
	public String toString() {
		return "Recette{" +
				"id=" + id +
				", titre='" + titre + '\'' +
				", tempsPreparation='" + tempsPreparation + '\'' +
				", ingredients=" + ingredients +
				", etapesPreparation='" + etapesPreparation + '\'' +
				", likes=" + likes +
				", utilisateursLikes=" + utilisateursLikes +
				", utilisateursVisites=" + utilisateursVisites +
				", categorie=" + categorie +
				'}';
	}

	public Recette() {
		super();
	}

	public Recette(String titre,
				   Category categorie,
				   String tempsPreparation,
				   List<String> ingredients,
					String etapesPreparation) {
		this.titre = titre;
		this.categorie = categorie;
		this.tempsPreparation = tempsPreparation;
		this.ingredients = ingredients;
		this.etapesPreparation = etapesPreparation;
	}
	
//

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getTempsPreparation() {
		return tempsPreparation;
	}

	public void setTempsPreparation(String tempsPreparation) {
		this.tempsPreparation = tempsPreparation;
	}

	public List<String> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<String> ingredients) {
		this.ingredients = ingredients;
	}

	public String getEtapesPreparation() {
		return etapesPreparation;
	}

	public void setEtapesPreparation(String etapesPreparation) {
		this.etapesPreparation = etapesPreparation;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public List<Utilisateur> getUtilisateursLikes() {
		return utilisateursLikes;
	}

	public void setUtilisateursLikes(List<Utilisateur> utilisateursLikes) {
		this.utilisateursLikes = utilisateursLikes;
	}

	public List<Utilisateur> getUtilisateursVisites() {
		return utilisateursVisites;
	}

	public void setUtilisateursVisites(List<Utilisateur> utilisateursVisites) {
		this.utilisateursVisites = utilisateursVisites;
	}

	public Category getCategorie() {
		return categorie;
	}

	public void setCategorie(Category categorie) {
		this.categorie = categorie;
	}
}
