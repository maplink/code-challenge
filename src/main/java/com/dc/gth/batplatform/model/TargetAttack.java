package com.dc.gth.batplatform.model;

public class TargetAttack {
	private String place;
	private Coordinate location;
	private Double probability;
	
	public TargetAttack(String place, Coordinate location, Double probability){
		this.place = place;
		this.location = location;
		this.probability = probability;
	}
	
	public String getPlace() {
		return place;
	}
	
	public void setPlace(String place) {
		this.place = place;
	}
	
	public Coordinate getLocation() {
		return location;
	}
	
	public void setLocation(Coordinate location) {
		this.location = location;
	}
	
	public Double getProbability() {
		return probability;
	}
	
	public void setProbability(Double probability) {
		this.probability = probability;
	}
}
