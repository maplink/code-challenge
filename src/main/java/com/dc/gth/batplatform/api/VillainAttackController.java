package com.dc.gth.batplatform.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.dc.gth.batplatform.model.Coordinate;
import com.dc.gth.batplatform.model.ResultAttack;
import com.dc.gth.batplatform.service.GeocodingService;
import com.dc.gth.batplatform.service.GeoutilsService;
import com.dc.gth.batplatform.service.VillainAttackService;
import com.dc.gth.batplatform.utils.ErrorValidationHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.slf4j.LoggerFactory;
import java.util.Optional;
import javax.inject.Inject;
import org.slf4j.Logger;

@RestController
@RequestMapping(value = "/api/villainattack", produces = MediaType.APPLICATION_JSON_UTF8_VALUE) 
public class VillainAttackController {
	private final static Logger LOGGER = LoggerFactory.getLogger(VillainAttackController.class); 

	@Inject
	VillainAttackService villainAttackService;

	@Inject
	ErrorValidationHandler errorValidationHandler;

	@Inject
	GeocodingService geocodingService;

	@Inject
	GeoutilsService geoutilsService; 
	
	@GetMapping(value = "/address")
	public ResponseEntity<?> getProbabilityAttackByAddress(@RequestParam("q") String address) {
		LOGGER.info("Getting villain attack probability by address.");
		
		Optional<Coordinate> villanLocation = this.geocodingService.getCoordinateByAddress(address);
		if(!villanLocation.isPresent())
			return this.errorValidationHandler.buildApiErrorResponse("restapi.notfound.location", HttpStatus.NOT_FOUND);
		
		ResultAttack result = this.villainAttackService.calculateVillainAttack(villanLocation.get());
		return ResponseEntity.ok().body(result);
	}

	@GetMapping(value = "/coordinate")
	public ResponseEntity<?> getProbabilityAttackByCoordinate(@RequestParam("q") String coordinate) {
		LOGGER.info("Getting villain attack probability by coordinate.");
		
		Optional<Coordinate> villanLocation = this.geoutilsService.getCoordinateFromString(coordinate);
		if(!villanLocation.isPresent())
			return this.errorValidationHandler.buildApiErrorResponse("restapi.badrequest.coordinates", HttpStatus.BAD_REQUEST);
		
		ResultAttack result = this.villainAttackService.calculateVillainAttack(villanLocation.get());
		return ResponseEntity.ok().body(result);
	}

	@GetMapping(value = "/location")
	public ResponseEntity<?> getProbabilityAttackByLocation(@RequestParam("q") String location) {
		LOGGER.info("Getting villain attack probability by location.");
		
		Optional<Coordinate> villanLocation = this.geocodingService.getCoordinateByLocation(location);
		if(!villanLocation.isPresent())
			return this.errorValidationHandler.buildApiErrorResponse("restapi.notfound.location", HttpStatus.NOT_FOUND);
		
		ResultAttack result = this.villainAttackService.calculateVillainAttack(villanLocation.get());
		return ResponseEntity.ok().body(result);
	}
}
