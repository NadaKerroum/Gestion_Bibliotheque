package com.example.Gestion_Bibliotheque.Service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.example.Gestion_Bibliotheque.DTO.LivreDTO;

public interface ILivreService {

	LivreDTO create(LivreDTO dto);

	List<LivreDTO> findAll();

	LivreDTO findById(Long id);

	List<LivreDTO> findByAuteurId(Long auteurId);

	LivreDTO updateFull(Long id, LivreDTO dto);
	
	LivreDTO updatePartial(Long id, LivreDTO dto);

	void delete(Long id);

}