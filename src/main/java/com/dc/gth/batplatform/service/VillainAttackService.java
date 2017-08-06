package com.dc.gth.batplatform.service;

import com.dc.gth.batplatform.model.Coordinate;
import com.dc.gth.batplatform.model.ResultAttack;

public interface VillainAttackService {

	public ResultAttack calculateVillainAttack(Coordinate villainLocation);
	public Double calculateAttackProbability(Coordinate villainLocation, Coordinate location);
}
