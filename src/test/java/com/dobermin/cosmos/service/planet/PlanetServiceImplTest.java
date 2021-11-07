package com.dobermin.cosmos.service.planet;

import com.dobermin.cosmos.entity.Lord;
import com.dobermin.cosmos.entity.Planet;
import com.dobermin.cosmos.repository.PlanetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class PlanetServiceImplTest {

	@Mock
	private PlanetRepository planetRepository;

	@InjectMocks
	private PlanetServiceImpl planetService;

	private Planet planet;
	private Planet newPlanet;

	@BeforeEach
	void setUp () {
		planet = new Planet("Mars");
		newPlanet = new Planet(planet.getName());
	}

	@Test
	void create () {
		newPlanet.setId(20L);
		when(planetRepository.save(planet)).thenReturn(newPlanet);
		Planet result = planetService.create(planet);
		assertEquals(20L, result.getId());
	}

	@Test
	void setLord () {
		Lord lord = new Lord("Neon", 120);
		Long id = 20L;
		lord.setId(10L);
		newPlanet.setId(id);
		when(planetRepository.findFirstById(id)).thenReturn(newPlanet);
		Planet nP = new Planet(newPlanet.getName());
		nP.setLord(lord);
		when(planetRepository.save(newPlanet)).thenReturn(nP);
		Planet result = planetService.setLord(id, lord);
		assertEquals(newPlanet.getLord(), result.getLord());
	}

	@Test
	void delete () {
		Long id = 10L;
		planetService.delete(id);
		verify(planetRepository).deleteById(id);
	}
}