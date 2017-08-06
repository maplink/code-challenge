package com.dc.gth.batplatform.service.impl;

import java.util.Optional;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dc.gth.batplatform.model.Coordinate;
import com.dc.gth.batplatform.model.LocationBounds;
import com.dc.gth.batplatform.service.GeoutilsService;

@Service
public class GeoutilsServiceImpl implements GeoutilsService {
	private final static Logger LOGGER = LoggerFactory.getLogger(GeoutilsServiceImpl.class);
	public static final Double EARTH_RADIUS = 6372.8; // In kilometers

	@Override
	public Double haversineDistance(Coordinate coordinate1, Coordinate coordinate2) {
		Function<Double, Double> haversine = val -> Math.pow(Math.sin(val / 2), 2);
		
		Double dLat = Math.toRadians(coordinate2.getLat() - coordinate1.getLat());
		Double dLon = Math.toRadians(coordinate2.getLng() - coordinate1.getLng());
		
		Double a = haversine.apply(dLat) + Math.cos(coordinate1.getLat()) * Math.cos(coordinate2.getLat()) *  haversine.apply(dLon); 
		Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		return Math.abs(EARTH_RADIUS * c - 1) * 1000;//in meters    
	}

	@Override
	public Boolean containsLocation(LocationBounds bounds, Coordinate location) {
		Double targetLatitude = location.getLat();
		Double targetLongitude = location.getLng();
		
		return targetLatitude >= bounds.getSouthWest().getLat() && targetLatitude <= bounds.getNorthEast().getLat()
				&& targetLongitude >= bounds.getSouthWest().getLng() && targetLongitude <= bounds.getNorthEast().getLng();
	}

	@Override
	public Optional<Coordinate> getCoordinateFromString(String coordinate) {
		try{
			String[] coordinates = coordinate.split(",");
			return Optional.of( new Coordinate(Double.valueOf(coordinates[0].trim()), Double.valueOf(coordinates[1].trim())) );
		}catch(Exception e){
			LOGGER.debug("getCoordinateFromString", e);
			return Optional.empty();
		}
	}
}
