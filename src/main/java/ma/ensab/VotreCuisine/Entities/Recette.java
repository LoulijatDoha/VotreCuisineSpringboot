package ma.ensab.VotreCuisine.Entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Recette {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String titre;

    private String categorie;

    @Column(name = "temps_preparation")
    private String tempsPreparation;


    @Column(name = "ingredients")
    private List<String> ingredients;

    @Column(name = "etapes_preparation", columnDefinition = "TEXT")
    private String etapesPreparation;

    private int likes;

    @ManyToMany
    @JoinTable(
        name = "recette_utilisateurs_likes",
        joinColumns = @JoinColumn(name = "recette_id"),
        inverseJoinColumns = @JoinColumn(name = "utilisateur_id")
    )
    private List<Utilisateur> utilisateursLikes;

    @ManyToMany
    @JoinTable(
        name = "recette_utilisateurs_visites",
        joinColumns = @JoinColumn(name = "recette_id"),
        inverseJoinColumns = @JoinColumn(name = "utilisateur_id")
    )
    private List<Utilisateur> utilisateursVisites;

    
    
    
    public Recette() {
		super();
	}

	public Recette(String titre, String categorie, String tempsPreparation, List<String> ingredients,
			String etapesPreparation) {
		super();
		this.titre = titre;
		this.categorie = categorie;
		this.tempsPreparation = tempsPreparation;
		this.ingredients = ingredients;
		this.etapesPreparation = etapesPreparation;
	}
	
	
	
	
	
	
	

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

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
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

	// Getters, setters et méthodes personnalisées
    public void ajouterLike(Utilisateur utilisateur) {
        if (!utilisateursLikes.contains(utilisateur)) {
            utilisateursLikes.add(utilisateur);
            likes++;
        }
    }

    public void retirerLike(Utilisateur utilisateur) {
        if (utilisateursLikes.remove(utilisateur)) {
            likes--;
        }
    }

    public void ajouterVisite(Utilisateur utilisateur) {
        if (!utilisateursVisites.contains(utilisateur)) {
            utilisateursVisites.add(utilisateur);
        }
    }
}
