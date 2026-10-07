package com.example.Gestion_Bibliotheque.Service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.example.Gestion_Bibliotheque.DTO.MembreDTO;

public interface IMembreService {

	MembreDTO create(MembreDTO dto);

	List<MembreDTO> findAll();

	MembreDTO findById(Long id);

	MembreDTO update(Long id, MembreDTO dto);

	void delete(Long id);

}