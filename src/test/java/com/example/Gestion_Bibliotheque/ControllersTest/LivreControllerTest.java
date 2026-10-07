package com.example.Gestion_Bibliotheque.ControllersTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.Gestion_Bibliotheque.Controller.LivreController;
import com.example.Gestion_Bibliotheque.DTO.LivreDTO;
import com.example.Gestion_Bibliotheque.Exception.ResourceNotFoundException;
import com.example.Gestion_Bibliotheque.Service.ILivreService;

import tools.jackson.databind.ObjectMapper;

@WebMvcTest(LivreController.class)
class LivreControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockitoBean
	private ILivreService service;

	private LivreDTO creerDto() {
		LivreDTO dto = new LivreDTO();
		dto.setTitre("Dernier Jour D'un Condamné");
		return dto;
	}

	@Test
	void create_retourne201() throws Exception {
		mockMvc.perform(post("/api/livres")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(creerDto())))
			.andExpect(status().isCreated());

		verify(service, times(1)).create(any(LivreDTO.class));
	}

	@Test
	void findAll_retourne200EtListe() throws Exception {
		when(service.findAll()).thenReturn(List.of(creerDto(), creerDto()));

		mockMvc.perform(get("/api/livres"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.length()").value(2))
			.andExpect(jsonPath("$[0].titre").value("Dernier Jour D'un Condamné"));
	}

	@Test
	void findByAuteur_retourne200() throws Exception {
		when(service.findByAuteurId(3L)).thenReturn(List.of(creerDto()));

		mockMvc.perform(get("/api/livres/auteur").param("auteurId", "3"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.length()").value(1))
			.andExpect(jsonPath("$[0].titre").value("Dernier Jour D'un Condamné"));
	}

	@Test
	void findByAuteur_sansParametre_retourne400() throws Exception {
		mockMvc.perform(get("/api/livres/auteur"))
			.andExpect(status().isBadRequest());
	}

	@Test
	void findById_retourne200() throws Exception {
		when(service.findById(1L)).thenReturn(creerDto());

		mockMvc.perform(get("/api/livres/1"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.titre").value("Dernier Jour D'un Condamné"));
	}

	@Test
	void findById_inexistant_retourne404() throws Exception {
		when(service.findById(99L)).thenThrow(new ResourceNotFoundException("livre introuvable"));

		mockMvc.perform(get("/api/livres/99"))
			.andExpect(status().isNotFound());
	}

	@Test
	void patch_retourne200() throws Exception {
		when(service.updatePartial(eq(1L), any(LivreDTO.class))).thenReturn(creerDto());

		mockMvc.perform(patch("/api/livres/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(creerDto())))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.titre").value("Dernier Jour D'un Condamné"));
	}

	@Test
	void put_retourne200() throws Exception {
		when(service.updateFull(eq(1L), any(LivreDTO.class))).thenReturn(creerDto());

		mockMvc.perform(put("/api/livres/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(creerDto())))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.titre").value("Dernier Jour D'un Condamné"));
	}

	@Test
	void put_inexistant_retourne404() throws Exception {
		when(service.updateFull(eq(99L), any(LivreDTO.class)))
			.thenThrow(new ResourceNotFoundException("livre introuvable"));

		mockMvc.perform(put("/api/livres/99")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(creerDto())))
			.andExpect(status().isNotFound());
	}

	@Test
	void delete_retourne204() throws Exception {
		mockMvc.perform(delete("/api/livres/1"))
			.andExpect(status().isNoContent());

		verify(service).delete(1L);
	}

	@Test
	void delete_inexistant_retourne404() throws Exception {
		org.mockito.Mockito.doThrow(new ResourceNotFoundException("livre introuvable"))
			.when(service).delete(99L);

		mockMvc.perform(delete("/api/livres/99"))
			.andExpect(status().isNotFound());
	}
}