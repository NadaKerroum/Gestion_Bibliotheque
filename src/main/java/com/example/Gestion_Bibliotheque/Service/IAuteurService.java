package com.example.Gestion_Bibliotheque.Service;

import java.util.List;

import com.example.Gestion_Bibliotheque.DTO.AuteurDTO;


public interface IAuteurService {

	void Create(AuteurDTO dto);

	List<AuteurDTO> findAll();

	AuteurDTO findById(Long id);

	AuteurDTO UpdatePartial(Long id, AuteurDTO dto);
	
	AuteurDTO UpdateFull(Long id, AuteurDTO dto);
	void Delete(Long id);

}