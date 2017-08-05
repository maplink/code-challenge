package com.dc.gth.batplatform.model;

import java.util.Collection;

public class ResultAttack {
	private Villain villain;
	private Collection<TargetAttack> targets;
	
	public Villain getVillain() {
		return villain;
	}
	public void setVillain(Villain villain) {
		this.villain = villain;
	}
	public Collection<TargetAttack> getTargets() {
		return targets;
	}
	public void setTargets(Collection<TargetAttack> targets) {
		this.targets = targets;
	}
}
