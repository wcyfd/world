package org.aimfd.world;

import org.aimfd.base.GameSocket;
import org.aimfd.world.planet.PlanetSystem;

public class WorldGameSocket extends GameSocket {

	private int maxPlanets;

	public WorldGameSocket() {
		super();
	}

	@Override
	protected void initSpecial() {
		PlanetSystem.initPlanets(maxPlanets);
	}
}
