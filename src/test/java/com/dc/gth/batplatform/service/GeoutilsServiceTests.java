package com.dc.gth.batplatform.service;

import javax.inject.Inject;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dc.gth.batplatform.model.Coordinate;
import com.dc.gth.batplatform.model.LocationBounds;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GeoutilsServiceTests {

	@Inject
	GeoutilsService geoutilsService;
	
	@Test
	public void shouldDetectWhenLocationIsInsideOfGotham(){
		LocationBounds gothamBounds = new LocationBounds(new Coordinate(40.746422, -73.994753), new Coordinate(40.763328,-73.968039));
		Coordinate gothamLocation = new Coordinate(40.757270059827206, -73.97850036621094);
		
		Boolean isInside = this.geoutilsService.containsLocation(gothamBounds, gothamLocation);
		
		assertTrue(isInside);
	}
	
	@Test
	public void shouldDetectWhenLocationIsOutsideOfGotham(){
		LocationBounds gothamBounds = new LocationBounds(new Coordinate(40.746422, -73.994753), new Coordinate(40.763328,-73.968039));
		Coordinate outsideGothamLocation = new Coordinate(30.757270059827206, -77.97850036621094);
		
		Boolean isInside = this.geoutilsService.containsLocation(gothamBounds, outsideGothamLocation);
		
		assertFalse(isInside);
	}
}
