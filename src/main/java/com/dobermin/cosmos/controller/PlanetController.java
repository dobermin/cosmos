package com.dobermin.cosmos.controller;

import com.dobermin.cosmos.entity.Lord;
import com.dobermin.cosmos.entity.Planet;
import com.dobermin.cosmos.service.lord.LordServiceImpl;
import com.dobermin.cosmos.service.planet.PlanetServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.FORBIDDEN;

@RestController
@RequestMapping("/planet")
public class PlanetController {

	private final PlanetServiceImpl planetService;
	private final LordServiceImpl lordService;

	public PlanetController (PlanetServiceImpl planetService, LordServiceImpl lordService) {
		this.planetService = planetService;
		this.lordService = lordService;
	}

	@PostMapping("/add")
	public ResponseEntity<?> create (@RequestBody @Validated Planet planet) {
		try {
			Planet newPlanet = planetService.create(planet);
			return ResponseEntity.ok().body(newPlanet);
		} catch (Exception e) {
			return ResponseEntity.status(FORBIDDEN).build();
		}
	}

	@PutMapping(value = {"/{planet_id}/lord/", "/{planet_id}/lord/{lord_name}"})
	public ResponseEntity<?> setLord (@PathVariable String planet_id, @PathVariable(required = false) String lord_name) {
		try {
			Lord lord = lordService.findByName(lord_name);
			planetService.setLord(Long.valueOf(planet_id), lord);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.status(FORBIDDEN).build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete (@PathVariable String id) {
		try {
			planetService.delete(Long.valueOf(id));
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.status(FORBIDDEN).build();
		}
	}

	@GetMapping
	public ResponseEntity<?> all () {
		try {
			List<Planet> planets = planetService.findAll();
			return ResponseEntity.ok().body(planets);
		} catch (Exception e) {
			return ResponseEntity.status(FORBIDDEN).build();
		}
	}
}
