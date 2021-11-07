package com.dobermin.cosmos.service.lord;

import com.dobermin.cosmos.entity.Lord;

import java.util.List;

public interface LordService {

	Lord create (Lord lord);

	List<Lord> findSlackers ();

	List<Lord> findYounger (Long limit);

	List<Lord> findAll ();

	Lord findByName(String name);
}
