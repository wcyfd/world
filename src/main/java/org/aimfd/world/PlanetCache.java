package org.aimfd.world;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.aimfd.world.planet.Planet;

public class PlanetCache {

	private final static Map<Integer, Planet> planetMap = new HashMap<>();
	private final static ConcurrentLinkedQueue<Integer> idlePlanetQueue = new ConcurrentLinkedQueue<>();

	public static void addPlanet(Planet planet) {
		planetMap.put(planet.getPlanetId(), planet);
		idlePlanetQueue.add(planet.getPlanetId());
	}

	public static Planet getPlanetById(int planetId) {
		return planetMap.get(planetId);
	}

	public static Planet borrowPlanet() {
		int planetId = idlePlanetQueue.poll();
		return planetMap.get(planetId);
	}

	public static void returnPlanet(Planet planet) {
		if (!idlePlanetQueue.contains(planet.getPlanetId())) {
			idlePlanetQueue.add(planet.getPlanetId());
		}
	}
}
