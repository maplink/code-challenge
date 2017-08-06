package com.dc.gth.batplatform.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.dc.gth.batplatform.model.LocationBounds;

@Component
@ConfigurationProperties("app")
public class AppConfiguration {
	private Integer radiusPlacesSearch;
	private Integer radiusJokerAttack;
	private LocationBounds gothamBounds;
	private String googleApiKey;
	
	public Integer getRadiusPlacesSearch() {
		return radiusPlacesSearch;
	}

	public void setRadiusPlacesSearch(Integer radiusPlacesSearch) {
		this.radiusPlacesSearch = radiusPlacesSearch;
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

	public String getGoogleApiKey() {
		return googleApiKey;
	}

	public void setGoogleApiKey(String googleApiKey) {
		this.googleApiKey = googleApiKey;
	}

	@Override
	public String toString(){
		return "RadiusPlacesSearch: " + this.radiusPlacesSearch + ", RadiusJokerAttack: " + this.radiusJokerAttack +
			", googleApiKey: " + this.googleApiKey;
	}
}
