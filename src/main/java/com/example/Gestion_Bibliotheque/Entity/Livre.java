package com.example.Gestion_Bibliotheque.Entity;


import jakarta.persistence.*;

@Entity
@Table(name="Livre")
public class Livre {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column (name="titre" , nullable = false)
	private String titre;
	
	@Column(name="isbn" , unique=true)
	private Long isbn ;
	
	@Column
	private Integer anneePublication;
	
	@Column(nullable=false)
	private Boolean disponible = true ;
	
	@ManyToOne
	@JoinColumn(name="auteurId")
	private Auteur auteur;
	

	public Long getId() {
		return id;
	}


	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public Long getIsbn() {
		return isbn;
	}

	public void setIsbn(Long isbn) {
		this.isbn = isbn;
	}

	public Integer getAnneePublication() {
		return anneePublication;
	}

	public void setAnneePublication(Integer anneePublication) {
		this.anneePublication = anneePublication;
	}

	public Boolean getDisponible() {
		return disponible;
	}

	public void setDisponible(Boolean disponible) {
		this.disponible = disponible;
	}

	public Auteur getAuteur() {
		return auteur;
	}

	public void setAuteur(Auteur auteur) {
		this.auteur = auteur;
	}


	
	
		
	

}
