package com.dc.gth.batplatform.service.impl;

import org.springframework.stereotype.Service;

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
	public static final int RADIUS_SEARCH_LIMIT = 3000;
	public static final int RADIUS_JOCKER_ACTION = 2000;
	public static final LocationBounds GOTHAM_BOUNDS = new LocationBounds(new Coordinate(40.746422, -73.994753), new Coordinate(40.763328,-73.968039));
	
	@Inject
	GeocodingService geocodingService;
	
	@Inject
	PlacesService placesService;
	
	@Inject
	GeoutilsService geoutilsService;
	
	@Override
	public ResultAttack calculateVillainAttack(Coordinate villainLocation) {
		Stream<Place> targetPlaces = this.placesService.getNearbyPlaces(villainLocation, RADIUS_SEARCH_LIMIT, GOTHAM_BOUNDS);
		Collection<TargetAttack> targets = targetPlaces
				.map(target -> new TargetAttack(target.getName(),target.getLocation(), this.calculateAttackProbability(villainLocation, target.getLocation())) )
				.collect(Collectors.toList());
		
		return new ResultAttack(new Villain("Joker",villainLocation), targets);
	}

	public Double calculateAttackProbability(Coordinate villainLocation, Coordinate location) {
		Double distance = geoutilsService.haversineDistance(villainLocation, location);
		return (RADIUS_JOCKER_ACTION - distance)/RADIUS_JOCKER_ACTION*(distance > RADIUS_JOCKER_ACTION ? 0.95 : 1);
	}

}
