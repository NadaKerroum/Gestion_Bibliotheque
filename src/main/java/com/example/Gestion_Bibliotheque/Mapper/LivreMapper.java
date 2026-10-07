package com.example.Gestion_Bibliotheque.Mapper;

import com.example.Gestion_Bibliotheque.DTO.LivreDTO;
import com.example.Gestion_Bibliotheque.Entity.Auteur;
import com.example.Gestion_Bibliotheque.Entity.Livre;
import org.springframework.stereotype.Component;
@Component
public class LivreMapper {
	
	public Livre ToEntity (LivreDTO dto, Auteur auteur) {
		Livre livre = new Livre();
		livre.setTitre(dto.getTitre());
		livre.setIsbn(dto.getIsbn());
		livre.setAnneePublication(dto.getAnneePublication());
		livre.setAuteur(auteur);
		return livre;
	}
	
	public LivreDTO ToDTO (Livre livre) {
		
		Auteur auteur =livre.getAuteur();
		LivreDTO dto = new LivreDTO();
		dto.SetId(livre.getId());
		dto.setTitre(livre.getTitre());
		dto.setIsbn(livre.getIsbn());
		dto.setAnneePublication(livre.getAnneePublication());
		dto.setAuteurId(auteur.getId());
		return dto;
	}

}
