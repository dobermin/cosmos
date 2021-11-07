package com.dobermin.cosmos.controller;

import com.dobermin.cosmos.entity.Lord;
import com.dobermin.cosmos.service.lord.LordServiceImpl;
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

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class LordControllerTest {

	@MockBean
	private LordServiceImpl lordService;

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper objectMapper;

	private Lord lord;
	private List<Lord> lords;
	private String urlTemplate;

	@BeforeEach
	void setUp () {
		lord = new Lord("Neon", 230);
		lords = new ArrayList<>() {
			{
				add(lord);
			}
		};
		urlTemplate = "/lord";
	}

	@Test
	void create_isTrue () throws Exception {
		this.mvc.perform(
				post(urlTemplate + "/add")
					.content(objectMapper.writeValueAsString(lord))
					.contentType(MediaType.APPLICATION_JSON)
			)
			.andExpect(status().isOk());
	}

	@Test
	void slackers_isTrue () throws Exception {
		when(lordService.findSlackers()).thenReturn(lords);
		this.mvc.perform(
				get(urlTemplate + "/slackers")
			)
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("Neon")));
	}

	@Test
	void slackers_isNull () throws Exception {
		when(lordService.findSlackers()).thenReturn(null);
		this.mvc.perform(
				get(urlTemplate + "/slackers")
			)
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("")));
	}

	@Test
	void slackers_isFalse () throws Exception {
		when(lordService.findSlackers()).thenThrow(NullPointerException.class);
		this.mvc.perform(
				get(urlTemplate + "/slackers")
			)
			.andExpect(status().isForbidden());
	}

	@Test
	void younger_isTrue () throws Exception {
		when(lordService.findYounger(2L)).thenReturn(lords);
		this.mvc.perform(
				get(urlTemplate + "/younger/2")
			)
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("Neon")));
	}

	@Test
	void younger_isNull () throws Exception {
		when(lordService.findYounger(2L)).thenReturn(null);
		this.mvc.perform(
				get(urlTemplate + "/younger/2")
			)
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("")));
	}

	@Test
	void younger_isFalse () throws Exception {
		when(lordService.findSlackers()).thenThrow(NullPointerException.class);
		this.mvc.perform(
				get(urlTemplate + "/younger/two")
			)
			.andExpect(status().isForbidden());
	}

	@Test
	void all_isTrue () throws Exception {
		when(lordService.findAll()).thenReturn(lords);
		this.mvc.perform(
				get(urlTemplate)
			)
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("Neon")));
	}

	@Test
	void all_isNull () throws Exception {
		when(lordService.findAll()).thenReturn(null);
		this.mvc.perform(
				get(urlTemplate)
			)
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("")));
	}

	@Test
	void all_isFalse () throws Exception {
		when(lordService.findAll()).thenThrow(NullPointerException.class);
		this.mvc.perform(
				get(urlTemplate)
			)
			.andExpect(status().isForbidden());
	}
}