package com.example.Gestion_Bibliotheque.Service;

import java.util.*;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Gestion_Bibliotheque.DTO.EmpruntDTO;
import com.example.Gestion_Bibliotheque.Entity.Emprunt;
import com.example.Gestion_Bibliotheque.Entity.Livre;
import com.example.Gestion_Bibliotheque.Entity.Membre;
import com.example.Gestion_Bibliotheque.Exception.ResourceNotFoundException;
import com.example.Gestion_Bibliotheque.GestionEmprunt.ValidateurEmprunt;
import com.example.Gestion_Bibliotheque.Mapper.EmpruntMapper;
import com.example.Gestion_Bibliotheque.Repository.EmpruntRepository;
import com.example.Gestion_Bibliotheque.Repository.LivreRepository;
import com.example.Gestion_Bibliotheque.Repository.MembreRepository;



@Service
public class EmpruntService {
	
	private final EmpruntRepository repo;
	private final EmpruntMapper map;
	private final MembreRepository membreRepo;
	private final LivreRepository livreRepo;
	private ValidateurEmprunt validateur;
	public EmpruntService(EmpruntRepository repo, EmpruntMapper map, MembreRepository membreRepo, LivreRepository livreRepo, ValidateurEmprunt validateur) {
		this.repo = repo;
		this.map = map;
		this.membreRepo = membreRepo;
		this.livreRepo = livreRepo;
		this.validateur=validateur;
	}
	
	private Livre findLivre(Long id) {
		Optional<Livre> l = livreRepo.findById(id);
		if (l.isEmpty()) {
			throw new ResourceNotFoundException("livre introuvable");
		}
		return l.get();
	}
	
	private Membre findMembre(Long id) {
		Optional<Membre> m = membreRepo.findById(id);
		if (m.isEmpty()) {
			throw new ResourceNotFoundException("membre introuvable");
		}
		return m.get();	
	}
	
	@Transactional
	public EmpruntDTO Create(EmpruntDTO dto) {
		Livre livre = findLivre(dto.getLivreId());
		Membre membre = findMembre(dto.getMembreId());
		validateur.ValiderEmprnt(livre, membre);
		
		
		Emprunt emprunt = map.ToEntity(livre,membre);
		return map.ToDTO(repo.save(emprunt));
	}
	
	
	public List<EmpruntDTO> findByLivreId(Long livreId){
		List<Emprunt> emprunts = repo.findByLivreId(livreId);
		List<EmpruntDTO> dtos = new ArrayList<>();
		for (Emprunt e : emprunts) {
			EmpruntDTO dto = map.ToDTO(e);
			dtos.add(dto);
		}
		return dtos;
	}
	
	public List<EmpruntDTO> findAll(){
		List<Emprunt> emprunt = repo.findAll();
		List<EmpruntDTO> dtos = new ArrayList<>();
		for (Emprunt e : emprunt) {
			EmpruntDTO empruntdto = map.ToDTO(e);
			dtos.add(empruntdto);
		}
		return dtos;
	}
	
	public EmpruntDTO update(Long id, Livre l , Membre m ) {
		Optional<Emprunt> emprunt = repo.findById(id);
		if (emprunt.isEmpty()) {
			throw new ResourceNotFoundException("aucune empreinte trouvée avec cet Id");
		}
		Emprunt em = emprunt.get();
		
		if (l != null) {
			em.setLivre(l);
		}
		
		if (m != null) {
			em.setMembre(m);
		}
		Emprunt save = repo.save(em);
		return map.ToDTO(save);
	}
	
	public void delete(Long id) {
		Optional<Emprunt> emprunt = repo.findById(id);
		if (emprunt.isEmpty()) {
			throw new ResourceNotFoundException("aucune empreinte trouvée avec cet Id");
		}
		Emprunt em = emprunt.get();
		repo.delete(em);
	}
	
	
}
