package org.aimfd.world.planet.environment.manager.module;

import org.aimfd.world.planet.Planet;
import org.aimfd.world.planet.environment.data.IEnvironmentData;

/**
 * 初始化模块
 * 
 * @author AIM
 *
 */
public class StartModule {

	private IEnvironmentData data;

	public StartModule(Planet planet) {
		this.data = planet.getPlanetAllData().getPlanetData().getEnviromentData();
	}

	public void onStart() {
		data.setOxygen(0);
		data.setTemperature(-20);
	}
}
