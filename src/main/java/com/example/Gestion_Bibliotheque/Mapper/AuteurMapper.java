package com.example.Gestion_Bibliotheque.Mapper;



import com.example.Gestion_Bibliotheque.DTO.AuteurDTO;
import com.example.Gestion_Bibliotheque.Entity.Auteur;
import org.springframework.stereotype.Component;
@Component
public class AuteurMapper {
	
	public Auteur ToEntity (AuteurDTO dto) {
		Auteur auteur = new Auteur();
		auteur.setNom(dto.getNom());
		auteur.setPrenom(dto.getPrenom());
		return auteur;
	}
	
	public AuteurDTO ToDTO(Auteur auteur) {
		
		AuteurDTO dto = new AuteurDTO();
		dto.setId(auteur.getId());
		dto.setNom(auteur.getNom());
		dto.setPrenom(auteur.getPrenom());
		
		return dto;
	}

}
