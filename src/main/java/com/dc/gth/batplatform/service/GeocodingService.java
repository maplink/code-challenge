package com.dc.gth.batplatform.service;

import java.util.Optional;
import com.dc.gth.batplatform.model.Coordinate;

public interface GeocodingService {
	Optional<Coordinate> getCoordinateByAddress(String Address);
	Optional<Coordinate> getCoordinateByLocation(String location);
}
