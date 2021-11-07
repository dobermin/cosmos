package com.dobermin.cosmos.repository;

import com.dobermin.cosmos.entity.Lord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LordRepository extends CrudRepository<Lord, Long> {

	List<Lord> findAllByPlanetsIsNullOrderByIdAsc ();

	List<Lord> findByOrderByAgeAsc ();

	List<Lord> findByOrderByIdAsc ();

	Lord findByName(String name);
}
