package com.dc.gth.batplatform.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.dc.gth.batplatform.model.Coordinate;
import com.dc.gth.batplatform.service.GeocodingService;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.ComponentFilter;
import com.google.maps.model.GeocodingResult;

@Service
public class GoogleGeocodingService implements GeocodingService{
	private final static Logger LOGGER = LoggerFactory.getLogger(GoogleGeocodingService.class);
	
	@Override
	public Optional<Coordinate> getCoordinateByAddress(String address) {
		GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyD2I6kTBjEbf4mRTck16QdxBiMO--446cA").build();
		try {
			GeocodingResult[] results = GeocodingApi
					.geocode(context, address).await();
			
			return getCoordinateFromGeoCodingResults(results);
		} catch (Exception e) {
			LOGGER.error("getCoordinateByAddress", e);
			return Optional.empty();
		}
	}

	@Override
	public Optional<Coordinate> getCoordinateByLocation(String location) {
		GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyD2I6kTBjEbf4mRTck16QdxBiMO--446cA").build();
		try {
			GeocodingResult[] results = GeocodingApi.newRequest(context)
					.components(ComponentFilter.locality(location))
					.await();
			
			return getCoordinateFromGeoCodingResults(results);
		} catch (Exception e) {
			LOGGER.error("getCoordinateByLocation", e);
			return Optional.empty();
		}
	}
	
	private Optional<Coordinate> getCoordinateFromGeoCodingResults(GeocodingResult[] results){
		return results.length > 0 ? Optional.of(new Coordinate(results[0].geometry.location.lat, results[0].geometry.location.lng)) : Optional.empty();
	}
}
