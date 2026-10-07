package com.example.Gestion_Bibliotheque.Entity;

import java.time.LocalDate;

import com.example.Gestion_Bibliotheque.Enumeration.MembreType;

import jakarta.persistence.*;

@Entity
@Table(name="Membre")
public class Membre {
      
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String nom;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable=false)
	private LocalDate dateInscription = LocalDate.now();
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
		private MembreType type = MembreType.STANDARD;
	
	 public MembreType getType() {
		return type;
	}

	public void setType(MembreType type) {
		this.type = type;
	}

	public void setId(Long id) {
		this.id = id;
	}


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

	 public LocalDate getDateInscription() {
		 return dateInscription;
	 }

	 public void setDateInscription(LocalDate dateInscription) {
		 this.dateInscription = dateInscription;
	 }	


}
