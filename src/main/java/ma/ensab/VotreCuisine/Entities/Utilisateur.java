package ma.ensab.VotreCuisine.Entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Inheritance (strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn (name="Type")
public abstract class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false, unique = true)
    private String password;
    
    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

	public Utilisateur(String username, String email, String password) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
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
