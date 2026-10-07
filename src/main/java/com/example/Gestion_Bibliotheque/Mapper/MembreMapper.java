package com.example.Gestion_Bibliotheque.Mapper;

import org.springframework.stereotype.Component;

import com.example.Gestion_Bibliotheque.DTO.MembreDTO;
import com.example.Gestion_Bibliotheque.Entity.Membre;

@Component
public class MembreMapper {
	
	public Membre ToEntity (MembreDTO dto) {
		
		Membre membre = new Membre();
		membre.setNom(dto.getNom());
		membre.setEmail(dto.getEmail());
		membre.setType(dto.getMembre());
		return membre;
	}
	
	public MembreDTO ToDTO (Membre membre) {
		MembreDTO dto = new MembreDTO();
		dto.setNom(membre.getNom());
		dto.setEmail(membre.getEmail());
		dto.setDateInscription(membre.getDateInscription());
		dto.setMembre(membre.getType());
		
		return dto;
		
	}
	

}
