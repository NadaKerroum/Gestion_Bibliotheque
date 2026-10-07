package com.example.Gestion_Bibliotheque.Controller;

import java.util.List;

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

import com.example.Gestion_Bibliotheque.DTO.LivreDTO;
import com.example.Gestion_Bibliotheque.Service.ILivreService;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/livres")
public class LivreController {

	private final ILivreService service;

	public LivreController(ILivreService service) {
		this.service = service;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void Create (@RequestBody LivreDTO dto) {
		service.create(dto);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<LivreDTO> findAll() {
		return service.findAll();
	}
	
	@GetMapping("/auteur")
	@ResponseStatus(HttpStatus.OK)
	public List<LivreDTO> findByAuteur( @RequestParam Long auteurId) {
		return service.findByAuteurId(auteurId);
	}
	
	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public LivreDTO findById(@PathVariable Long id) {
		return service.findById(id);
	}
	
	@PatchMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public LivreDTO patchLivre(@PathVariable Long id , @RequestBody LivreDTO dto) {
		return service.updatePartial(id, dto);
	}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public LivreDTO putLivre(@PathVariable Long id , @RequestBody LivreDTO dto) {
		return service.updateFull(id, dto);
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		 service.delete(id);
	}
}
