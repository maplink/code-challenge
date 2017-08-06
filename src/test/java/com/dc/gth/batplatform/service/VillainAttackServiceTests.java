package com.dc.gth.batplatform.service;
import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import com.dc.gth.batplatform.model.Coordinate;
import com.dc.gth.batplatform.model.Place;
import com.dc.gth.batplatform.model.ResultAttack;
import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.*;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VillainAttackServiceTests {

	@MockBean
	GeocodingService geocodingService; 
	
	@MockBean
	PlacesService placesService;
	
	@MockBean
	GeoutilsService geoutilsService;
	
	@Inject
	VillainAttackService villainAttackService;
	
	@Test
	public void shouldRetrieveVillainAndTargetPlaces(){
		Coordinate villainLocation = new Coordinate(40.754671, -73.988568);
		Place[] places = {new Place("place 1", new Coordinate(40.74497, -73.967562)), new Place("place 2", new Coordinate(40.74497, -73.967562))}; 
		given(this.placesService.getNearbyPlaces(eq(villainLocation), any(), any()))
			.willReturn(Stream.of(places));
		
		ResultAttack result = villainAttackService.calculateVillainAttack(villainLocation);
		
		assertThat(result).hasFieldOrProperty("villain");
		assertThat(result).hasFieldOrProperty("targets");
		assertThat(result.getVillain()).hasFieldOrPropertyWithValue("name", "Joker");
		assertThat(result.getVillain()).hasFieldOrProperty("location");
		assertThat(result.getVillain().getLocation()).hasFieldOrPropertyWithValue("lat", 40.754671);
		assertThat(result.getVillain().getLocation()).hasFieldOrPropertyWithValue("lng", -73.988568);
		
		assertThat(result.getTargets()).hasSize(2);
		assertThat(result.getTargets()).element(0).hasFieldOrPropertyWithValue("place", places[0].getName());
		assertThat(result.getTargets()).element(0).hasFieldOrPropertyWithValue("location",places[0].getLocation());
		assertThat(result.getTargets()).element(1).hasFieldOrPropertyWithValue("place", places[1].getName());
		assertThat(result.getTargets()).element(1).hasFieldOrPropertyWithValue("location",places[1].getLocation());
	}

	@Test
	public void shouldGetProbabilityWithDistanceCloseToMiddleJockerRadius(){
		given(this.geoutilsService.haversineDistance(any(), any())).willReturn(1000.0);
		
		Double probability = this.villainAttackService.calculateAttackProbability(new Coordinate(41.084671, -73.988562), new Coordinate(40.754671, -73.988568));
		
		assertThat(probability).isCloseTo(0.5, offset(0.05));
	}
	
	@Test
	public void shouldGetProbabilityWithDistanceCloseToJockerRadiusLimit(){
		given(this.geoutilsService.haversineDistance(any(), any())).willReturn(1970.0);
		
		Double probability = this.villainAttackService.calculateAttackProbability(new Coordinate(41.084671, -73.988562), new Coordinate(40.754671, -73.988568));

		assertThat(probability).isCloseTo(0.01, offset(0.005));
	}
	
	@Test
	public void shouldGetProbabilityWithDistanceCloseToTargetLocation(){
		given(this.geoutilsService.haversineDistance(any(), any())).willReturn(10.0);
		
		Double probability = this.villainAttackService.calculateAttackProbability(new Coordinate(41.084671, -73.988562), new Coordinate(40.754671, -73.988568));
		
		assertThat(probability).isCloseTo(0.98, offset(0.05));
	}
}
