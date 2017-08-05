package com.dc.gth.batplatform.service.impl;

import java.util.Collection;
import java.util.Collections;

import org.springframework.stereotype.Service;

import com.dc.gth.batplatform.model.Coordinate;
import com.dc.gth.batplatform.model.LocationBounds;
import com.dc.gth.batplatform.model.Place;
import com.dc.gth.batplatform.service.PlacesService;

@Service
public class GooglePlaceService implements PlacesService{

	@Override
	public Collection<Place> getNearestPlaces(Coordinate location, Integer radiusLimit, LocationBounds bounds) {
		// TODO Implementation
		return Collections.EMPTY_LIST;
	}

}
