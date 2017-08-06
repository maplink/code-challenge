package com.dc.gth.batplatform.integration.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dc.gth.batplatform.model.Coordinate;
import com.dc.gth.batplatform.model.LocationBounds;
import com.dc.gth.batplatform.model.Place;
import com.dc.gth.batplatform.service.PlacesService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlaceServiceIntegrationTests {

	@Inject
	PlacesService placeService;
	
	@Test
	public void shouldGetNearbyPlacesInsideGotham(){
		Coordinate requestedLocation = new Coordinate(40.75603475927267, -73.98244857788086);
		LocationBounds gothamBounds = new LocationBounds(new Coordinate(40.746422, -73.994753), new Coordinate(40.763328, -73.968039));
		Integer radiusSearchLimit = 3000;
		
		List<Place> places = this.placeService.getNearbyPlaces(requestedLocation, radiusSearchLimit, gothamBounds).collect(Collectors.toList());

		assertThat(places.isEmpty()).isFalse();
		assertThat(places).filteredOnNull("name").isEmpty();
		assertThat(places).filteredOnNull("location").isEmpty();
	}
}
