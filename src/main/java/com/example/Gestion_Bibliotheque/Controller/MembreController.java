package com.example.Gestion_Bibliotheque.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import com.example.Gestion_Bibliotheque.DTO.MembreDTO;
import com.example.Gestion_Bibliotheque.Service.IMembreService;


@RestController
@RequestMapping("api/membres")
public class MembreController {
	
	private final IMembreService service;

	public MembreController(IMembreService service) {
			this.service = service;
		}
		
		
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public MembreDTO create(@RequestBody MembreDTO dto) {
		return service.create(dto);
		}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<MembreDTO> getAll() {
		return service.findAll();
		}
		
		
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public MembreDTO getById(@PathVariable Long id) {
		return service.findById(id);
		}
		
		
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public MembreDTO update(@PathVariable Long id, @RequestBody MembreDTO dto) {
		return service.update(id, dto);
		}
		

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		service.delete(id);
		}
	
	
}
	
	
	

	


