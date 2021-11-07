package com.dobermin.cosmos.repository;

import com.dobermin.cosmos.entity.Planet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanetRepository extends CrudRepository<Planet, Long> {

	Planet findFirstById(Long id);

	List<Planet> findByOrderByNameAsc();
}
