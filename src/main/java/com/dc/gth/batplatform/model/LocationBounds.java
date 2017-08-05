package com.dc.gth.batplatform.model;

public class LocationBounds {
	private Coordinate southWest;
	private Coordinate northEast;
	
	public Coordinate getSouthWest() {
		return southWest;
	}
	
	public void setSouthWest(Coordinate southWest) {
		this.southWest = southWest;
	}
	
	public Coordinate getNorthEast() {
		return northEast;
	}
	
	public void setNorthEast(Coordinate northEast) {
		this.northEast = northEast;
	}
}
