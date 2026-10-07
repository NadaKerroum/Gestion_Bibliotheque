package com.example.Gestion_Bibliotheque.DTO;



import jakarta.validation.constraints.*;

public class AuteurDTO {
	
	
	private Long id;
	
	@NotBlank
	private String nom;
	
	@NotBlank
	private String prenom ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id ;
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
