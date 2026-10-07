package com.example.Gestion_Bibliotheque.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.Gestion_Bibliotheque.DTO.EmpruntDTO;
import com.example.Gestion_Bibliotheque.Entity.Livre;
import com.example.Gestion_Bibliotheque.Entity.Membre;
import com.example.Gestion_Bibliotheque.Service.EmpruntService;

@RestController
@RequestMapping("/api/emprunts")
public class EmpruntController {

	private final EmpruntService service;

	public EmpruntController(EmpruntService service) {
		this.service = service;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EmpruntDTO create(@RequestBody EmpruntDTO dto) {
		return service.Create(dto);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<EmpruntDTO> findAll() {
		return service.findAll();
	}

	@GetMapping("/livre/{livreId}")
	@ResponseStatus(HttpStatus.OK)
	public List<EmpruntDTO> findByLivreId(@PathVariable Long livreId) {
		return service.findByLivreId(livreId);
	}

	@PatchMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public EmpruntDTO update(@PathVariable Long id, @RequestParam(required = false) Livre livre,  @RequestParam(required = false) Membre membre) {
		return service.update(id, livre, membre);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
}