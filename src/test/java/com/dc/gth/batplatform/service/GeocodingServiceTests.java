package com.dc.gth.batplatform.service;

import java.util.Optional;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;
import com.dc.gth.batplatform.model.Coordinate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GeocodingServiceTests {

	@Inject
	GeocodingService geocodingService;
	
	@Test
	public void shouldGetValidCoordinateByAddress(){
		String address = "6275 Bryan Park, Browns Summit";
		Coordinate expectedCoordinate = new Coordinate(36.17956, -79.733562999);
		Optional<Coordinate> location = this.geocodingService.getCoordinateByAddress(address);
		
		assertThat(location.isPresent()).isTrue();
		assertThat(location.get().getLat()).isCloseTo(expectedCoordinate.getLat(), offset(0.005));
		assertThat(location.get().getLng()).isCloseTo(expectedCoordinate.getLng(), offset(0.005));
	}
	
	@Test
	public void shouldNotGetCoordinateByAddress(){
		String address = "Orange clockwork transpoitting interestelar abracadabra";
		Optional<Coordinate> location = this.geocodingService.getCoordinateByAddress(address);
		
		assertThat(location.isPresent()).isFalse();
	}
	
	@Test
	public void shouldGetValidCoordinateByLocation(){
		String placeLocation = "Bryan Park";
		Coordinate expectedCloseCoordinate = new Coordinate(37.5971901, -77.46710899);
		Optional<Coordinate> location = this.geocodingService.getCoordinateByLocation(placeLocation);
		
		assertThat(location.isPresent()).isTrue();
		assertThat(location.get().getLat()).isCloseTo(expectedCloseCoordinate.getLat(), offset(0.5));
		assertThat(location.get().getLng()).isCloseTo(expectedCloseCoordinate.getLng(), offset(0.5));
	}
	
	@Test
	public void shouldDontGetCoordinateByLocation(){
		String address = "Joker, are you kidding me?";
		Optional<Coordinate> location = this.geocodingService.getCoordinateByAddress(address);
		
		assertThat(location.isPresent()).isFalse();
	}
}
