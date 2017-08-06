package com.dc.gth.batplatform.service;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.List;
import java.util.stream.Collectors;
import com.dc.gth.batplatform.model.Coordinate;
import com.dc.gth.batplatform.model.LocationBounds;
import com.dc.gth.batplatform.model.Place;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlaceServiceTests {
	@MockBean
	GeoutilsService geoutilsService;
	
	@Inject
	PlacesService placeService;
	
	@Test
	public void shouldGetNearbyPlacesInsideGotham(){
		Coordinate requestedLocation = new Coordinate(40.75661990450191, -73.98845672607422);
		LocationBounds gothamBounds = new LocationBounds(new Coordinate(40.746422, -73.994753), new Coordinate(40.763328,-73.968039));
		given(this.geoutilsService.containsLocation(any(), any())).willReturn(true);
		
		List<Place> places = this.placeService.getNearbyPlaces(requestedLocation, 3000, gothamBounds).collect(Collectors.toList());

		assertThat(places.isEmpty()).isFalse();
		assertThat(places).filteredOnNull("name").isEmpty();
		assertThat(places).filteredOnNull("location").isEmpty();
	}
	
	@Test
	public void shouldNotGetNearbyPlacesOutsideGotham(){
		Coordinate requestedLocation = new Coordinate(40.75661990450191, -73.98845672607422);
		LocationBounds gothamBounds = new LocationBounds(new Coordinate(40.746422, -73.994753), new Coordinate(40.763328,-73.968039));
		given(this.geoutilsService.containsLocation(any(), any())).willReturn(false);
		
		List<Place> places = this.placeService.getNearbyPlaces(requestedLocation, 3000, gothamBounds).collect(Collectors.toList());

		assertThat(places).isEmpty();
	}
}
