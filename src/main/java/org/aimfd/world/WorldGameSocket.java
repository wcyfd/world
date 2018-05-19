package org.aimfd.world;

import org.aimfd.base.GameSocket;
import org.aimfd.world.planet.PlanetSystem;
import org.aimfd.world.system.ASystem;

public class WorldGameSocket extends GameSocket {

	public WorldGameSocket() {
		super();
	}

	@Override
	protected void initSpecial() {
		// 系统初始化
		ASystem.init();

		// 星球初始化
		PlanetSystem.initPlanets(1000);
	}
}
