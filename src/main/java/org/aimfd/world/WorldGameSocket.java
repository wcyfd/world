package org.aimfd.world;

import org.aimfd.base.GameSocket;
import org.aimfd.world.planet.PlanetSystem;

public class WorldGameSocket extends GameSocket {

	public WorldGameSocket() {
		super();
	}

	@Override
	protected void initSpecial() {
		PlanetSystem.initPlanets(1000);
	}
}
