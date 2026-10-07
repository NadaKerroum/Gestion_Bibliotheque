package com.example.Gestion_Bibliotheque.Service;



import org.springframework.stereotype.Service;
import java.util.*;
import com.example.Gestion_Bibliotheque.DTO.AuteurDTO;
import com.example.Gestion_Bibliotheque.Entity.Auteur;
import com.example.Gestion_Bibliotheque.Exception.ResourceNotFoundException;
import com.example.Gestion_Bibliotheque.Mapper.AuteurMapper;
import com.example.Gestion_Bibliotheque.Repository.AuteurRepository;

import jakarta.transaction.Transactional;

@Service
public class AuteurService implements IAuteurService  {
	
	private final AuteurRepository repo;
	private final AuteurMapper map;
	
	public AuteurService(AuteurRepository repo, AuteurMapper map) {
		this.repo = repo;
		this.map = map;
	}	
	
	@Override
	public void Create (AuteurDTO dto) {
		Auteur a = map.ToEntity(dto);
		repo.save(a);
	}
	
	@Override
	public List<AuteurDTO> findAll(){
		List<Auteur> auteur = repo.findAll();
		List<AuteurDTO> dtos = new ArrayList<>();
		for (Auteur a : auteur) {
			AuteurDTO dto = map.ToDTO(a);
			dtos.add(dto);
			
		}
		return dtos;
	}
	//!!! cette classe retourne une entité !!!
	private Auteur find(Long id) {
		Optional<Auteur> a = repo.findById(id);
		 if(a.isEmpty()) {
			 throw new ResourceNotFoundException("Auteur introuvable");
		 }
		 return a.get();
	}
	
	
	@Override
	public AuteurDTO findById(Long id)  {
		 return map.ToDTO(find(id));
	}
	
	@Override
	@Transactional
	public AuteurDTO UpdatePartial(Long id, AuteurDTO dto ) {
		 Auteur auteur = find(id);
		
		 if (dto.getNom() != null) {
			 auteur.setNom(dto.getNom());
		 }
		 
		 if (dto.getPrenom() != null) {
			 auteur.setPrenom(dto.getPrenom());	
		 }
		 repo.save(auteur);
		 return map.ToDTO(auteur);
	}
	
	@Override
	@Transactional
	public AuteurDTO UpdateFull(Long id, AuteurDTO dto ) {
		 Auteur auteur = find(id);
		 
		 auteur.setNom(dto.getNom());
		 auteur.setPrenom(dto.getPrenom());	
		 repo.save(auteur);
		 return map.ToDTO(auteur);
	}
	
	@Override
	@Transactional
	public void Delete(Long id) {
		Auteur auteur = find(id);
		repo.delete(auteur);
	}
	

}
