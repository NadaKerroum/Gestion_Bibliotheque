package com.example.Gestion_Bibliotheque.Service;
import java.util.*;

import org.springframework.stereotype.Service;

import com.example.Gestion_Bibliotheque.DTO.MembreDTO;
import com.example.Gestion_Bibliotheque.Entity.Membre;
import com.example.Gestion_Bibliotheque.Exception.*;
import com.example.Gestion_Bibliotheque.Mapper.MembreMapper;
import com.example.Gestion_Bibliotheque.Repository.MembreRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MembreService implements IMembreService {

	
	private final MembreRepository repo;
	private final MembreMapper map;
	
	public MembreService(MembreRepository repo , MembreMapper map) {
		this.repo = repo;
		this.map = map;
	}
	
	@Override
	public MembreDTO create (MembreDTO dto) {
		if (repo.existsByEmail(dto.getEmail())) {
	        throw new EmailDejeExistantException("L'email est deja utilisé");
	    }
		Membre membre = map.ToEntity(dto);
		return map.ToDTO(repo.save(membre));
	}
	
	private Membre findMembre(Long id) {
		Optional<Membre> m = repo.findById(id);
		if(m.isEmpty()) {
			throw new ResourceNotFoundException("membre introuvable");
		}
		Membre membre = m.get();
		return membre;
	}
	
	
	@Override
	public List<MembreDTO> findAll(){
		List<MembreDTO> dtos = new ArrayList<>();
		List<Membre> membre = repo.findAll();
		
		for(Membre m : membre) {
			MembreDTO dto = map.ToDTO(m);
			dtos.add(dto);
		}
		return dtos;		
	}
	
	@Override
	public MembreDTO findById(Long id) {
		Membre membre = findMembre(id);
		return map.ToDTO(membre);
	}
	
	
	@Override
	@Transactional
	public MembreDTO update (Long id , MembreDTO dto) {
		Membre membre = findMembre(id);
		
		if (dto.getNom() != null) {
			membre.setNom(dto.getNom());
		}
		
		if (dto.getEmail() != null && !dto.getEmail().equals(membre.getEmail())) {
		    if (repo.existsByEmail(dto.getEmail())) {
		        throw new EmailDejeExistantException("email deja existant");
		    }
		    membre.setEmail(dto.getEmail());
		}
		
		
		
		if(dto.getMembre() != null) {
			membre.setType(dto.getMembre());
		}
		
	
		return map.ToDTO(repo.save(membre));
	}
	
	@Override
	@Transactional
	public void delete(Long id) {
		Membre m = findMembre(id);
		repo.delete(m);
		
	}
	
}
