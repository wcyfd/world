package org.aimfd.world.planet.environment.manager.module;

import org.aimfd.world.planet.Planet;
import org.aimfd.world.planet.environment.data.IEnvironmentData;

/**
 * 氧气模块
 * 
 * @author AIM
 *
 */
public class OxygenModule {
	private IEnvironmentData data;

	public OxygenModule(Planet planet) {
		data = planet.getPlanetAllData().getPlanetData().getEnviromentData();
	}

	public void addOxygen(int oxygen) {
		data.setOxygen(data.getOxygen() + oxygen);
	}
}
