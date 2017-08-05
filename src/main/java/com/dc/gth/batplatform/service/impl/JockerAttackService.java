package com.dc.gth.batplatform.service.impl;

import org.springframework.stereotype.Service;

import com.dc.gth.batplatform.model.Coordinate;
import com.dc.gth.batplatform.model.ResultAttack;
import com.dc.gth.batplatform.service.GeocodingService;
import com.dc.gth.batplatform.service.VillainAttackService;
import javax.inject.Inject;

@Service
public class JockerAttackService implements VillainAttackService{

	@Inject
	GeocodingService geocodingService;
	
	@Override
	public ResultAttack calculateVillainAttack(Coordinate villainLocation) {
		// TODO Implementation
		return new ResultAttack();
	}

}
