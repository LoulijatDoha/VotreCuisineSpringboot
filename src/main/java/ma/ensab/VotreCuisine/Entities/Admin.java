package ma.ensab.VotreCuisine.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;

@Entity
@DiscriminatorValue("admin") // Valeur de discrimination pour cette sous-classe
public class Admin extends Utilisateur {
	
	
	

	
	public Admin() {
		super();
	}

	public Admin(String username, String email, String password) {
		super(username, email, password);
	}

	
	
	
	/*public void supprimerRecette(Recette recette) {
        // Implémentation pour supprimer une recette (si nécessaire)
    }

    public void modifierRecette(Recette recette, String nouveauTitre, String nouvelleCategorie) {
        recette.setTitre(nouveauTitre);
        recette.setCategorie(nouvelleCategorie);
    }*/
}
