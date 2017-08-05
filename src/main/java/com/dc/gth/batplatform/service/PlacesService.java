package com.dc.gth.batplatform.service;

import java.util.Collection;

import com.dc.gth.batplatform.model.Coordinate;
import com.dc.gth.batplatform.model.LocationBounds;
import com.dc.gth.batplatform.model.Place;

public interface PlacesService {
	Collection<Place>getNearestPlaces(Coordinate location, Integer radiusLimit, LocationBounds bounds);
}
