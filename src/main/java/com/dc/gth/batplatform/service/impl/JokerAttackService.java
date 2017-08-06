package com.dc.gth.batplatform.service.impl;

import org.springframework.stereotype.Service;

import com.dc.gth.batplatform.config.AppConfiguration;
import com.dc.gth.batplatform.model.Coordinate;
import com.dc.gth.batplatform.model.LocationBounds;
import com.dc.gth.batplatform.model.Place;
import com.dc.gth.batplatform.model.ResultAttack;
import com.dc.gth.batplatform.model.TargetAttack;
import com.dc.gth.batplatform.model.Villain;
import com.dc.gth.batplatform.service.GeocodingService;
import com.dc.gth.batplatform.service.GeoutilsService;
import com.dc.gth.batplatform.service.PlacesService;
import com.dc.gth.batplatform.service.VillainAttackService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.TooManyListenersException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Inject;

@Service
public class JokerAttackService implements VillainAttackService{
	
	@Inject
	GeocodingService geocodingService;
	
	@Inject
	PlacesService placesService;
	
	@Inject
	GeoutilsService geoutilsService;
	
	@Inject
	AppConfiguration appConfiguration;
	
	@Override
	public ResultAttack calculateVillainAttack(Coordinate villainLocation) {
		Stream<Place> targetPlaces = this.placesService.getNearbyPlaces(villainLocation, this.appConfiguration.getRadiusPlaceSearch(), this.appConfiguration.getGothamBounds());
		Collection<TargetAttack> targets = targetPlaces
				.map(target -> new TargetAttack(target.getName(),target.getLocation(), this.calculateAttackProbability(villainLocation, target.getLocation())) )
				.collect(Collectors.toList());
		
		return new ResultAttack(new Villain("Joker",villainLocation), targets);
	}

	public Double calculateAttackProbability(Coordinate villainLocation, Coordinate location) {
		Double distance = geoutilsService.haversineDistance(villainLocation, location);
		Integer jokerRadiusAttack = this.appConfiguration.getRadiusJokerAttack();
		Integer radiusSearchLimit = this.appConfiguration.getRadiusJokerAttack();
		
		if(jokerRadiusAttack >= distance)
			return (jokerRadiusAttack - distance)/jokerRadiusAttack;
		else if(radiusSearchLimit >= distance)
			return (radiusSearchLimit - distance)/radiusSearchLimit * 0.95;

		return 0.0;
	}

}
