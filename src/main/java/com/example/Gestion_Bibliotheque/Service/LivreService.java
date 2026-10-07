package com.example.Gestion_Bibliotheque.Service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.example.Gestion_Bibliotheque.DTO.LivreDTO;
import com.example.Gestion_Bibliotheque.Entity.Auteur;
import com.example.Gestion_Bibliotheque.Entity.Livre;
import com.example.Gestion_Bibliotheque.Exception.ResourceNotFoundException;
import com.example.Gestion_Bibliotheque.Mapper.LivreMapper;
import com.example.Gestion_Bibliotheque.Repository.AuteurRepository;
import com.example.Gestion_Bibliotheque.Repository.LivreRepository;
import org.springframework.transaction.annotation.Transactional;


@Service
public class LivreService implements ILivreService {
	
	private final LivreRepository repo;
	private final LivreMapper map;
	private final AuteurRepository Autrepo;
	
	public LivreService(LivreRepository repo , LivreMapper map,  AuteurRepository Autrepo) {
		this.map = map;
		this.repo=repo;
		this.Autrepo=Autrepo;
	}
	

	private Auteur findAuteur(Long auteurId) {
		Optional<Auteur> auteur = Autrepo.findById(auteurId);
		if(auteur.isEmpty()) {
			throw new ResourceNotFoundException("AuteurIntrouvable");
		}
		Auteur a = auteur.get();
		return a;
	}
	
	@Override
	@Transactional
	public LivreDTO create (LivreDTO dto) {
		
		if (repo.existsByIsbn(dto.getIsbn())) {
		    throw new IllegalStateException("ISBN déjà utilisé : " + dto.getIsbn());
		}
		Auteur auteur = findAuteur(dto.getAuteurId());
		Livre livre = map.ToEntity(dto,auteur);
		Livre l = repo.save(livre);
		return map.ToDTO(l);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<LivreDTO> findAll() {
		List <Livre> livres = repo.findAll();
		List <LivreDTO> dtos = new ArrayList<>();
		
		for(Livre l : livres) {
			LivreDTO dto = map.ToDTO(l);
			dtos.add(dto);
		}
		
		return dtos;
	}
	

	private Livre find (Long id) {
		Optional<Livre> l = repo.findById(id);
		if (l.isEmpty()) {
			throw new ResourceNotFoundException	("livre introuvable");
		}
		Livre livre = l.get();
		return livre;
	}
	
	@Override
	@Transactional(readOnly = true)
	public LivreDTO findById(Long id) {
		Livre l = find(id);
		LivreDTO dto = map.ToDTO(l);
		return dto;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<LivreDTO> findByAuteurId(Long auteurId) {
		List<LivreDTO> dtos = new ArrayList<>();
		List <Livre> livres = repo.findByAuteurId(auteurId);
		for (Livre l : livres) {
			LivreDTO dto = map.ToDTO(l);
			dtos.add(dto);
		}
		
		return dtos;
	}
	
	@Override
	@Transactional
	public LivreDTO updatePartial(Long id, LivreDTO dto) {
		Livre l = find(id);
		if (dto.getTitre() != null) {
			l.setTitre(dto.getTitre());
		}
		
		if (dto.getIsbn() != null && !dto.getIsbn().equals(l.getIsbn())) {
		    if (repo.existsByIsbn(dto.getIsbn())) {
		        throw new RuntimeException("ISBN déjà utilisé : " + dto.getIsbn());
		    }
		    l.setIsbn(dto.getIsbn());
		}
		
		if(dto.getAnneePublication() != null) {
			l.setAnneePublication(dto.getAnneePublication());
		}
		if(dto.getAuteurId() != null) {
			l.setAuteur(findAuteur(dto.getAuteurId()));
		}
		
		Livre lv = repo.save(l);
		dto = map.ToDTO(lv);
		return dto;
	}
	
	@Override
	@Transactional
	public LivreDTO updateFull(Long id, LivreDTO dto) {
		Livre l = find(id);
		
			l.setTitre(dto.getTitre());
		    if (repo.existsByIsbn(dto.getIsbn())) {
		        throw new RuntimeException("ISBN déjà utilisé : " + dto.getIsbn());
		    }
		    l.setIsbn(dto.getIsbn());
			l.setAnneePublication(dto.getAnneePublication());
			l.setAuteur(findAuteur(dto.getAuteurId()));
		Livre lv = repo.save(l);
		dto = map.ToDTO(lv);
		return dto;
	}
	
	
	@Override
	@Transactional
	public void delete (Long id) {
		Livre l = find(id);
		repo.delete(l);
	}
	
	
}
