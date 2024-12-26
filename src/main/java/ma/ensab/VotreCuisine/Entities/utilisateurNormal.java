package ma.ensab.VotreCuisine.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;

@Entity
@DiscriminatorValue("normalUser") // Valeur de discrimination pour cette sous-classe
public class utilisateurNormal extends Utilisateur {
	
	

    public utilisateurNormal() {
		super();
	}

	public utilisateurNormal(String username, String email, String password) {
		super(username, email, password);
	}
	
	
	

	/*public void ajouterLike(Recette recette) {
        if (!getRecettesLikees().contains(recette)) {
            getRecettesLikees().add(recette);
            recette.ajouterLike(this);
        }
    }

    public void retirerLike(Recette recette) {
        if (getRecettesLikees().remove(recette)) {
            recette.retirerLike(this);
        }
    }

    public void consulterRecette(Recette recette) {
        if (!getRecettesVisitees().contains(recette)) {
            getRecettesVisitees().add(recette);
            recette.ajouterVisite(this);
        }
    }*/
}
