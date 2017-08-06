package com.dc.gth.batplatform.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.dc.gth.batplatform.model.LocationBounds;

@Component
@ConfigurationProperties("app")
public class AppConfiguration {
	private Integer radiusPlaceSearch;
	private Integer radiusJokerAttack;
	private LocationBounds gothamBounds;
	
	public Integer getRadiusPlaceSearch() {
		return radiusPlaceSearch;
	}
	
	public void setRadiusPlaceSearch(Integer radiusPlaceSearch) {
		this.radiusPlaceSearch = radiusPlaceSearch;
	}
	
	public Integer getRadiusJokerAttack() {
		return radiusJokerAttack;
	}
	
	public void setRadiusJokerAttack(Integer radiusJokerAttack) {
		this.radiusJokerAttack = radiusJokerAttack;
	}
	
	public LocationBounds getGothamBounds() {
		return gothamBounds;
	}
	
	public void setGothamBounds(LocationBounds gothamBounds) {
		this.gothamBounds = gothamBounds;
	}

}


