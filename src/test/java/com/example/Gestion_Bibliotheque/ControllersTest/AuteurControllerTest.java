package com.example.Gestion_Bibliotheque.ControllersTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import tools.jackson.databind.ObjectMapper;

import com.example.Gestion_Bibliotheque.Controller.AuteurController;
import com.example.Gestion_Bibliotheque.DTO.AuteurDTO;
import com.example.Gestion_Bibliotheque.Exception.ResourceNotFoundException;
import com.example.Gestion_Bibliotheque.Service.IAuteurService;

@WebMvcTest(AuteurController.class)
class AuteurControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockitoBean
	private IAuteurService service;

	private AuteurDTO creerDto() {
		AuteurDTO dto = new AuteurDTO();
		dto.setNom("Hugo");         
		return dto;
	}

	@Test
	void create_retourne201() throws Exception {
		mockMvc.perform(post("/api/auteurs")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(creerDto())))
			.andExpect(status().isCreated());

		verify(service, times(1)).Create(any(AuteurDTO.class));
	}

	@Test
	void findAll_retourne200EtListe() throws Exception {
		when(service.findAll()).thenReturn(List.of(creerDto(), creerDto()));

		mockMvc.perform(get("/api/auteurs"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.length()").value(2))
			.andExpect(jsonPath("$[0].nom").value("Hugo"));
	}

	@Test
	void findById_retourne200() throws Exception {
		when(service.findById(1L)).thenReturn(creerDto());

		mockMvc.perform(get("/api/auteurs/1"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.nom").value("Hugo"));
	}

	@Test
	void findById_inexistant_retourne404() throws Exception {
		when(service.findById(99L)).thenThrow(new ResourceNotFoundException("auteur introuvable"));

		mockMvc.perform(get("/api/auteurs/99"))
			.andExpect(status().isNotFound());
	}

	@Test
	void put_retourne200() throws Exception {
		when(service.UpdateFull(eq(1L), any(AuteurDTO.class))).thenReturn(creerDto());

		mockMvc.perform(put("/api/auteurs/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(creerDto())))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.nom").value("Hugo"));
	}

	@Test
	void patch_retourne200() throws Exception {
		when(service.UpdatePartial(eq(1L), any(AuteurDTO.class))).thenReturn(creerDto());

		mockMvc.perform(patch("/api/auteurs/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(creerDto())))
			.andExpect(status().isOk());
	}

	@Test
	void delete_retourne204() throws Exception {
		mockMvc.perform(delete("/api/auteurs/1"))
			.andExpect(status().isNoContent());

		verify(service).Delete(1L);
	}
}