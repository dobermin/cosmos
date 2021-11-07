package com.dobermin.cosmos.service.lord;

import com.dobermin.cosmos.entity.Lord;
import com.dobermin.cosmos.repository.LordRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LordServiceImpl implements LordService {

	private final LordRepository lordRepository;

	public LordServiceImpl (LordRepository lordRepository) {
		this.lordRepository = lordRepository;
	}

	@Override
	public Lord create (Lord lord) {
		return lordRepository.save(lord);
	}

	@Override
	public List<Lord> findSlackers () {
		return lordRepository.findAllByPlanetsIsNullOrderByIdAsc();
	}

	@Override
	public List<Lord> findYounger (Long limit) {
		return lordRepository.findByOrderByAgeAsc().stream()
			.limit(limit)
			.collect(Collectors.toList());
	}

	@Override
	public List<Lord> findAll () {
		return lordRepository.findByOrderByIdAsc();
	}

	@Override
	public Lord findByName (String name) {
		if (name == null) return null;
		return lordRepository.findByName(name);
	}
}