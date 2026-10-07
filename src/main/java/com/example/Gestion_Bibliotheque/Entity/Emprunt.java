package com.example.Gestion_Bibliotheque.Entity;

import java.time.LocalDate;


import jakarta.persistence.*;

@Entity
@Table(name="Emprunt")
public class Emprunt {
	
	 public static final int DUREE_JOURS = 14;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="livreId")
	private Livre livre;
	
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="membreId")
	private Membre membre;
	
	
	@Column
	private LocalDate dateEmprunt;
	
	@Column
	private LocalDate dateRetourPrevue;
	
	@Column
	private LocalDate dateRetourEffective;
	
	public Emprunt(Livre livre, Membre membre) {
        this.livre = livre;
        this.membre = membre;
        this.dateEmprunt = LocalDate.now();
        this.dateRetourPrevue = dateEmprunt.plusDays(DUREE_JOURS);
    }

	public Long getId() {
		return id;
	}

	public Livre getLivre() {
		return livre;
	}

	public void setLivre(Livre livre) {
		this.livre = livre;
	}

	public Membre getMembre() {
		return membre;
	}

	public void setMembre(Membre membre) {
		this.membre = membre;
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
