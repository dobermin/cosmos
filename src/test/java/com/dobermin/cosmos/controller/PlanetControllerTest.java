package com.dobermin.cosmos.controller;

import com.dobermin.cosmos.entity.Lord;
import com.dobermin.cosmos.entity.Planet;
import com.dobermin.cosmos.repository.LordRepository;
import com.dobermin.cosmos.repository.PlanetRepository;
import com.dobermin.cosmos.service.planet.PlanetServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class PlanetControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private PlanetRepository planetRepository;

	@MockBean
	private PlanetServiceImpl planetService;

	@MockBean
	private LordRepository lordRepository;

	private Planet planet;
	private String urlTemplate;

	@BeforeEach
	void setUp () {
		planet = new Planet("Mars");
		urlTemplate = "/planet";
	}

	@Test
	void create_isTrue () throws Exception {
		this.mvc.perform(
				post(urlTemplate + "/add")
					.content(objectMapper.writeValueAsString(planet))
					.contentType(MediaType.APPLICATION_JSON)
			)
			.andExpect(status().isOk());
	}

	@Test
	void setLord_isTrue () throws Exception {
		Lord lord = new Lord("Neon", 230);
		Lord newLord = new Lord(lord.getName(), lord.getAge());
		newLord.setId(17L);
		when(lordRepository.save(lord)).thenReturn(newLord);
		Planet newPlanet = new Planet(planet.getName());
		newPlanet.setId(7L);
		when(planetRepository.save(planet)).thenReturn(newPlanet);
		this.mvc.perform(
				put(urlTemplate + "/7/lord/" + newLord.getName())
			)
			.andExpect(status().isOk());
	}

	@Test
	void setLord_isBad () throws Exception {
		this.mvc.perform(
				put(urlTemplate + "/seven/lord")
					.content(objectMapper.writeValueAsString(null))
					.contentType(MediaType.APPLICATION_JSON)
			)
			.andExpect(status().is4xxClientError());
	}

	@Test
	void setLord_isFalse () throws Exception {
		Lord lord = new Lord("Neon", 230);
		lord.setId(10L);
		this.mvc.perform(
				put(urlTemplate + "/seven/lord")
					.content(objectMapper.writeValueAsString(lord))
					.contentType(MediaType.APPLICATION_JSON)
			)
			.andExpect(status().is4xxClientError());
	}

	@Test
	void delete_isTrue () throws Exception {
		Long id = 7L;
		Planet newPlanet = new Planet(planet.getName());
		newPlanet.setId(id);
		when(planetRepository.save(planet)).thenReturn(newPlanet);
		this.mvc.perform(
				delete(urlTemplate + "/" + id)
			)
			.andExpect(status().isOk());
	}

	@Test
	void delete_isBad () throws Exception {
		this.mvc.perform(
				delete(urlTemplate + "/")
			)
			.andExpect(status().is4xxClientError());
	}

	@Test
	void delete_isFalse () throws Exception {
		this.mvc.perform(
				delete(urlTemplate + "/two")
			)
			.andExpect(status().isForbidden());
	}
}