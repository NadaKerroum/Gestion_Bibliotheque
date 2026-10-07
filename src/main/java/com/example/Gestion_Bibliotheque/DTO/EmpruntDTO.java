	package com.example.Gestion_Bibliotheque.DTO;

import java.time.LocalDate;
import jakarta.validation.constraints.NotNull;

public class EmpruntDTO {
	
	private Long id;
	
	@NotNull
	private Long livreId;
	
	private String livreTitre;
	
	@NotNull
	private Long membreId;
	private String membreNom;
	private LocalDate dateEmprunt;
	private LocalDate dateRetourPrevue;
	private LocalDate dateRetourEffective;
	
	public Long getId() {
		return id;
	}

	public Long getLivreId() {
		return livreId;
	}
	public void setLivreId(Long livreId) {
		this.livreId = livreId;
	}
	public String getLivreTitre() {
		return livreTitre;
	}
	public void setLivreTitre(String livreTitre) {
		this.livreTitre = livreTitre;
	}
	public Long getMembreId() {
		return membreId;
	}
	public void setMembreId(Long membreId) {
		this.membreId = membreId;
	}
	public String getMembreNom() {
		return membreNom;
	}
	public void setMembreNom(String membreNom) {
		this.membreNom = membreNom;
	}
	public LocalDate getDateEmprunt() {
		return dateEmprunt;
	}
	public void setDateEmprunt(LocalDate dateEmprunt) {
		this.dateEmprunt = dateEmprunt;
	}
	public LocalDate getDateRetourPrevue() {
		return dateRetourPrevue;
	}
	public void setDateRetourPrevue(LocalDate dateRetourPrevue) {
		this.dateRetourPrevue = dateRetourPrevue;
	}
	public LocalDate getDateRetourEffective() {
		return dateRetourEffective;
	}
	public void setDateRetourEffective(LocalDate dateRetourEffective) {
		this.dateRetourEffective = dateRetourEffective;
	}
	
		

}
