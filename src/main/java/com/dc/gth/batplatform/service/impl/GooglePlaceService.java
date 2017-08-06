package com.dc.gth.batplatform.service.impl;

import java.util.Arrays;
import java.util.stream.Stream;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dc.gth.batplatform.config.AppConfiguration;
import com.dc.gth.batplatform.model.Coordinate;
import com.dc.gth.batplatform.model.LocationBounds;
import com.dc.gth.batplatform.model.Place;
import com.dc.gth.batplatform.service.GeoutilsService;
import com.dc.gth.batplatform.service.PlacesService;
import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlacesSearchResponse;

@Service
public class GooglePlaceService implements PlacesService {
	private final static Logger LOGGER = LoggerFactory.getLogger(GooglePlaceService.class);
			
	@Inject 
	GeoutilsService geoutilsService;
	
	@Inject
	AppConfiguration appConfiguration;

	@Override
	public Stream<Place> getNearbyPlaces(Coordinate location, Integer radiusLimit, LocationBounds bounds) {
		return getNearbyPlaces(location, radiusLimit, bounds, this.appConfiguration.getGoogleApiKey());
	}

	public Stream<Place> getNearbyPlaces(Coordinate location, Integer radiusLimit, LocationBounds bounds, String apiKey) {
		GeoApiContext context = new GeoApiContext.Builder().apiKey(apiKey).build();
		
		try{
			PlacesSearchResponse response = PlacesApi.nearbySearchQuery(context, new LatLng(location.getLat(), location.getLng()))
				.radius(radiusLimit)
				.await();
			return Arrays.stream(response.results).filter(result -> this.geoutilsService.containsLocation(bounds, new Coordinate(result.geometry.location.lat, result.geometry.location.lng)))
					.map(result -> new Place(result.name, new Coordinate(result.geometry.location.lat, result.geometry.location.lng)));

		}catch(Exception e){
			LOGGER.error("getNearbyPlaces", e);
			return Stream.empty();
		}
	}

}
