package com.example.Gestion_Bibliotheque.DTO;

import java.time.LocalDate;

import com.example.Gestion_Bibliotheque.Enumeration.MembreType;

import jakarta.validation.constraints.*;

public class MembreDTO {
	
	private Long id;
	
	@NotBlank
	private String nom;
	
	@NotBlank
	private String email;
	
	@NotBlank
	private MembreType membre;
	
		
	public Long getId() {
		return id;
	}

	public String getNom() {
		return nom;	
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public MembreType getMembre() {
		return membre;
	}


	public void setMembre(MembreType membre) {
		this.membre = membre;
	}


	public LocalDate getDateInscription() {
		return dateInscription;
	}


	public void setDateInscription(LocalDate dateInscription) {
		this.dateInscription = dateInscription;
	}


	private LocalDate dateInscription;

}
