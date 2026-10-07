package com.example.Gestion_Bibliotheque.Controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.Gestion_Bibliotheque.DTO.AuteurDTO;
import com.example.Gestion_Bibliotheque.Service.IAuteurService;

@RestController
@RequestMapping("/api/auteurs")
public class AuteurController {

	private final IAuteurService service;

	public AuteurController(IAuteurService service) {
		this.service = service;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody AuteurDTO dto) {
		 service.Create(dto);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<AuteurDTO> findAll() {
		return service.findAll();
	}
	
	
	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public AuteurDTO findById(@PathVariable Long id) {
		return service.findById(id);
	}

	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public AuteurDTO putAuteur(@PathVariable Long id , @RequestBody AuteurDTO dto  ) {
		return service.UpdateFull(id, dto);
	}
	
	@PatchMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public AuteurDTO patchAuteur(@PathVariable Long id , @RequestBody AuteurDTO dto  ) {
		return service.UpdatePartial(id, dto);
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAuteur(@PathVariable Long id ) {
		service.Delete(id);
	}
	

	
}
