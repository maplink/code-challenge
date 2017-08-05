package com.dc.gth.batplatform.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@RestController
@RequestMapping(value = "/api/villainattack", produces = MediaType.APPLICATION_JSON_UTF8_VALUE) 
public class VillainAttackController {
	private final static Logger LOGGER = LoggerFactory.getLogger(VillainAttackController.class); 
	
	@GetMapping(value = "/address")
	public ResponseEntity<?> getProbabilityAttackByAddress(@RequestParam("q") String address) {
		LOGGER.info("Getting villain attack probability by address.");
		return ResponseEntity.ok().build();
	}
	
	@GetMapping(value = "/coordinate")
	public ResponseEntity<?> getProbabilityAttackByCoordinate(@RequestParam("q") String coordinate) {
		LOGGER.info("Getting villain attack probability by coordinate.");
		return ResponseEntity.ok().build();
	}
	
	@GetMapping(value = "/location")
	public ResponseEntity<?> getProbabilityAttackByLocation(@RequestParam("q") String location) {
		LOGGER.info("Getting villain attack probability by location.");
		return ResponseEntity.ok().build();
	}
	
}
