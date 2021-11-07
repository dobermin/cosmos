package com.dobermin.cosmos.service.lord;

import com.dobermin.cosmos.entity.Lord;
import com.dobermin.cosmos.repository.LordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class LordServiceImplTest {

	@Mock
	private LordRepository lordRepository;

	@InjectMocks
	private LordServiceImpl lordService;

	private Lord lord;
	private List<Lord> lords;

	@BeforeEach
	void setUp () {
		lord = new Lord("Neon", 230);
		lords = new ArrayList<>() {
			{
				add(lord);
			}
		};
	}

	@Test
	void create () {
		Lord newLord = new Lord(lord.getName(), lord.getAge());
		newLord.setId(10L);
		when(lordRepository.save(lord)).thenReturn(newLord);
		Lord result = lordService.create(lord);
		assertEquals(10L, result.getId());
	}

	@Test
	void findSlackers_isTrue () {
		when(lordRepository.findAllByPlanetsIsNullOrderByIdAsc()).thenReturn(lords);
		List<Lord> result = lordService.findSlackers();
		assertEquals(1, result.size());
	}

	@Test
	void findSlackers_isNull () {
		when(lordRepository.findAllByPlanetsIsNullOrderByIdAsc()).thenReturn(null);
		List<Lord> result = lordService.findSlackers();
		assertNull(result);
	}

	@Test
	void findYounger () {
		when(lordRepository.findByOrderByAgeAsc()).thenReturn(lords);
		List<Lord> result = lordService.findYounger(2L);
		assertEquals(1, result.size());
	}

	@Test
	void findAll () {
		when(lordRepository.findByOrderByIdAsc()).thenReturn(lords);
		List<Lord> result = lordService.findAll();
		assertEquals(1, result.size());
	}
}