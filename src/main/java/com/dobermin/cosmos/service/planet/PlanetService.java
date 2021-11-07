package com.dobermin.cosmos.service.planet;

import com.dobermin.cosmos.entity.Lord;
import com.dobermin.cosmos.entity.Planet;

import java.util.List;

public interface PlanetService {

	Planet create (Planet planet);

	Planet setLord (Long id, Lord lord);

	void delete (Long id);

	Planet findById (Long id);

	List<Planet> findAll();
}
