package org.aimfd.world.planet.environment.manager;

import org.aimfd.world.planet.PlanetManager;
import org.aimfd.world.planet.environment.IEnvironmentPublic;
import org.aimfd.world.planet.environment.manager.module.OxygenModule;
import org.aimfd.world.planet.environment.manager.module.StartModule;
import org.aimfd.world.planet.environment.manager.module.TemperatureModule;

public class PlanetEnvironmentManager extends PlanetManager implements IEnvironmentPublic {

	private StartModule startModule;
	private TemperatureModule temperatureModule;
	private OxygenModule oxygenModule;

	@Override
	public void init() {
		startModule = new StartModule(planet);
	}

	@Override
	protected void onStart() {
		startModule.onStart();
	}

	@Override
	public void addTemperature(int temperature) {
		temperatureModule.addTemperature(temperature);
	}

	@Override
	public void addOxygen(int oxygen) {
		oxygenModule.addOxygen(oxygen);
	}

}
