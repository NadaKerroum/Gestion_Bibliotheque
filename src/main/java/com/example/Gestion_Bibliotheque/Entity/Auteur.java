package com.example.Gestion_Bibliotheque.Entity;



import jakarta.persistence.*;

@Entity
@Table(name = "Auteur")
public class Auteur {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column (name="nom" , nullable = false )
	private String nom;
	
	@Column (name="prenom" , nullable = false )
	private String prenom;
	

	  public Long getId() {
		  return id;
	  }

	  public String getNom() {
		  return nom;
	  }

	  public void setNom(String nom) {
		  this.nom = nom;
	  }

	  public String getPrenom() {
		  return prenom;
	  }

	  public void setPrenom(String prenom) {
		  this.prenom = prenom;
	  }


	  
	 
}
