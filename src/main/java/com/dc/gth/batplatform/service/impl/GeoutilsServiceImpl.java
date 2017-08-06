package com.dc.gth.batplatform.service.impl;

import org.springframework.stereotype.Service;

import com.dc.gth.batplatform.model.Coordinate;
import com.dc.gth.batplatform.model.LocationBounds;
import com.dc.gth.batplatform.service.GeoutilsService;

@Service
public class GeoutilsServiceImpl implements GeoutilsService {
	public static final Double R = 6372.8; // In kilometers

	@Override
	public Double haversineDistance(Coordinate coordinate1, Coordinate coordinate2) {
		Double dLat = Math.toRadians(coordinate2.getLat() - coordinate1.getLat());
		Double dLon = Math.toRadians(coordinate2.getLng() - coordinate1.getLng());
		Double a = Math.pow(Math.sin(dLat/2),2) + Math.pow(Math.sin(dLon/2),2) * Math.cos(Math.toRadians(coordinate1.getLat()) * Math.cos(Math.toRadians(coordinate2.getLat())));
		return 2 * R * Math.asin(Math.sqrt(a));			      
	}

	@Override
	public Boolean containsLocation(LocationBounds bounds, Coordinate location) {
		Double targetLatitude = location.getLat();
		Double targetLongitude = location.getLng();
		
		return targetLatitude >= bounds.getSouthWest().getLat() && targetLatitude <= bounds.getNorthEast().getLat()
				&& targetLongitude >= bounds.getSouthWest().getLng() && targetLongitude <= bounds.getNorthEast().getLng();
	}
}
