package com.dc.gth.batplatform.service;

import java.util.Optional;

import com.dc.gth.batplatform.model.Coordinate;
import com.dc.gth.batplatform.model.LocationBounds;

public interface GeoutilsService {
	public Double haversineDistance(Coordinate coordinate1, Coordinate coordinate2);
	public Boolean containsLocation(LocationBounds bounds, Coordinate location);
	public Optional<Coordinate> getCoordinateFromString(String coordinate);
}
