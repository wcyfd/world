package org.aimfd.world.planet.environment.manager.module;

import org.aimfd.world.planet.Planet;
import org.aimfd.world.planet.environment.data.IEnvironmentData;

/**
 * 温度模块
 * 
 * @author AIM
 *
 */
public class TemperatureModule {

	private IEnvironmentData data;

	public TemperatureModule(Planet planet) {
		data = planet.getPlanetAllData().getPlanetData().getEnviromentData();
	}

	public void addTemperature(int temperature) {
		data.setTemperature(data.getTemperature() + temperature);
	}
}
