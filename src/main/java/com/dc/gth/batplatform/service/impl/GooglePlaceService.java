package com.dc.gth.batplatform.service.impl;

import java.util.Arrays;
import java.util.stream.Stream;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
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

	@Inject 
	GeoutilsService geoutilsService;
	
	@Override
	public Stream<Place> getNearbyPlaces(Coordinate location, Integer radiusLimit, LocationBounds bounds) {
		GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyD2I6kTBjEbf4mRTck16QdxBiMO--446cA").build();
		
		try{
			PlacesSearchResponse response = PlacesApi.nearbySearchQuery(context, new LatLng(location.getLat(), location.getLng()))
				.radius(radiusLimit)
				.await();
			return Arrays.stream(response.results).filter(result -> this.geoutilsService.containsLocation(bounds, new Coordinate(result.geometry.location.lat, result.geometry.location.lng)))
					.map(result -> new Place(result.name, new Coordinate(result.geometry.location.lat, result.geometry.location.lng)));

		}catch(Exception e){
			return Stream.empty();
		}
	}

}
