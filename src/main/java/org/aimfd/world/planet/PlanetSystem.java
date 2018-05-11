package org.aimfd.world.planet;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

public class PlanetSystem {
	public final static ConcurrentLinkedQueue<Integer> planetsIdQueue = new ConcurrentLinkedQueue<>();
	public final static Map<Integer, Planet> planetCache = new HashMap<>();

	public static void initPlanets(int planetCount) {
		for (int i = 0; i < planetCount; i++) {
			planetsIdQueue.add(i);

			Planet planet = new Planet(i);
			planet.registerPlanetManager();

			planetCache.put(i, planet);
		}
	}

	public static void join(int planetId) {

	}

}
