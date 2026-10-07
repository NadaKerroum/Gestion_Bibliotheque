package com.example.Gestion_Bibliotheque.Mapper;

import java.time.LocalDate;

import com.example.Gestion_Bibliotheque.DTO.EmpruntDTO;
import com.example.Gestion_Bibliotheque.Entity.Emprunt;
import com.example.Gestion_Bibliotheque.Entity.Livre;
import com.example.Gestion_Bibliotheque.Entity.Membre;
import org.springframework.stereotype.Component;
@Component
public class EmpruntMapper {
	public Emprunt ToEntity(Livre l , Membre m) {

		Emprunt emprunt = new Emprunt(l , m);	
		emprunt.setDateEmprunt(LocalDate.now());
		return emprunt;
	}
	
	public  EmpruntDTO ToDTO(Emprunt empreint) {
		
		Livre l = empreint.getLivre();
		Membre m = empreint.getMembre();
		
		EmpruntDTO dto = new EmpruntDTO();
		
		dto.setDateEmprunt(empreint.getDateEmprunt());
		dto.setDateRetourEffective(empreint.getDateRetourEffective());
		dto.setDateRetourPrevue(empreint.getDateRetourPrevue());
		dto.setLivreId(l.getId());
		dto.setMembreId(m.getId());
		dto.setMembreNom(m.getNom());
		
		return dto;
	}


}
