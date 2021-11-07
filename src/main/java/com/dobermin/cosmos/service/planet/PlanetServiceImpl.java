package com.dobermin.cosmos.service.planet;

import com.dobermin.cosmos.entity.Lord;
import com.dobermin.cosmos.entity.Planet;
import com.dobermin.cosmos.repository.PlanetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanetServiceImpl implements PlanetService {

	private final PlanetRepository planetRepository;

	public PlanetServiceImpl (PlanetRepository planetRepository) {
		this.planetRepository = planetRepository;
	}

	@Override
	public Planet create (Planet planet) {
		return planetRepository.save(planet);
	}

	@Override
	public Planet setLord (Long id, Lord lord) {
		Planet planet = findById(id);
		planet.setLord(lord);
		return planetRepository.save(planet);
	}

	@Override
	public void delete (Long id) {
		planetRepository.deleteById(id);
	}

	@Override
	public Planet findById (Long id) {
		return planetRepository.findFirstById(id);
	}

	@Override
	public List<Planet> findAll () {
		return planetRepository.findByOrderByNameAsc();
	}
}
