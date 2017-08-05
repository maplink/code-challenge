package com.dc.gth.batplatform.service.impl;

import java.util.Optional;
import org.springframework.stereotype.Service;
import com.dc.gth.batplatform.model.Coordinate;
import com.dc.gth.batplatform.service.GeocodingService;

@Service
public class GoogleGeocodingService implements GeocodingService{

	@Override
	public Optional<Coordinate> getCoordinateByAddress(String Address) {
		// TODO Implementation
		return Optional.empty();
	}

	@Override
	public Optional<Coordinate> getCoordinateByLocation(String location) {
		// TODO Implementation
		return Optional.empty();
	}
}
