package com.example.Gestion_Bibliotheque.DTO;

import jakarta.validation.constraints.*;

public class LivreDTO {
	
	private Long id;
	
	@NotBlank
	private String titre;	
	
	@NotBlank
	private Long isbn;
	
	@Min(0)
	@NotNull
	private Integer anneePublication;	
	
	private Boolean disponible = true ;
	
	private Long auteurId;
	
	public Long getId() {
		return id;
	}
    
	public void SetId(Long id) {
		this.id = id;
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

	public Long getAuteurId() {
		return auteurId;
	}

	public void setAuteurId(Long auteurId) {
		this.auteurId = auteurId;
	}


	

}
