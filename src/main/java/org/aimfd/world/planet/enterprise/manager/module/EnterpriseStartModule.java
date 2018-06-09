package org.aimfd.world.planet.enterprise.manager.module;

import org.aimfd.world.planet.Planet;
import org.aimfd.world.planet.enterprise.data.IEnterpriseData;

public class EnterpriseStartModule {
	private Planet planet;
	private IEnterpriseData enterpriseData;

	public EnterpriseStartModule(Planet planet) {
		this.planet = planet;
	}
	
	public void onStart() {
	}
}
