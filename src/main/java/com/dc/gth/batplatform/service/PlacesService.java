package com.dc.gth.batplatform.service;

import java.util.stream.Stream;
import com.dc.gth.batplatform.model.Coordinate;
import com.dc.gth.batplatform.model.LocationBounds;
import com.dc.gth.batplatform.model.Place;

public interface PlacesService {
	Stream<Place>getNearbyPlaces(Coordinate location, Integer radiusLimit, LocationBounds bounds);
}
