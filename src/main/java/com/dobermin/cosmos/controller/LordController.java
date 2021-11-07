package com.dobermin.cosmos.controller;

import com.dobermin.cosmos.entity.Lord;
import com.dobermin.cosmos.service.lord.LordServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.FORBIDDEN;

@RestController
@RequestMapping("/lord")
public class LordController {

	private final LordServiceImpl lordService;

	public LordController (LordServiceImpl lordService) {
		this.lordService = lordService;
	}

	@PostMapping(value = "/add")
	public ResponseEntity<?> create (@RequestBody @Validated Lord lord) {
		try {
			Lord newLord = lordService.create(lord);
			return ResponseEntity.ok().body(newLord);
		} catch (Exception e) {
			return ResponseEntity.status(FORBIDDEN).build();
		}
	}

	@GetMapping("/slackers")
	public ResponseEntity<?> slackers () {
		try {
			List<Lord> slackers = lordService.findSlackers();
			return ResponseEntity.ok()
				.body(slackers);
		} catch (Exception e) {
			return ResponseEntity.status(FORBIDDEN).build();
		}
	}

	@GetMapping("/younger/{id}")
	public ResponseEntity<?> younger (@PathVariable String id) {
		try {
			List<Lord> younger = lordService.findYounger(Long.valueOf(id));
			return ResponseEntity.ok()
				.body(younger);
		} catch (Exception e) {
			return ResponseEntity.status(FORBIDDEN).build();
		}
	}

	@GetMapping
	public ResponseEntity<?> all () {
		try {
			List<Lord> lords = lordService.findAll();
			return ResponseEntity.ok()
				.body(lords);
		} catch (Exception e) {
			return ResponseEntity.status(FORBIDDEN).build();
		}
	}
}
