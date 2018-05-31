package org.aimfd.world;

import org.aimfd.world.planet.Planet;
import org.aimfd.world.planet.PlanetAllData;
import org.aimfd.world.player.Player;
import org.aimfd.world.player.PlayerAllData;
import org.aimfd.world.system.ASystem;
import org.aimfd.world.system.SystemAllData;

public class AData {

	private static AData aData = new AData();

	private ASystem aSystem;

	protected static void initPlayerData(int maxCount) {
		for (int clientId = 0; clientId < maxCount; clientId++) {
			Player player = new Player(clientId);
			PlayerCache.addPlayer(player);
		}
	}

	protected static void initPlanetData(int maxCount) {
		for (int planetId = 0; planetId < maxCount; planetId++) {
			Planet planet = new Planet(planetId);
			PlanetCache.addPlanet(planet);
		}
	}

	protected static void initSystemData() {
		aData.aSystem = new ASystem();
	}

	/**
	 * 获得系统
	 * 
	 * @return
	 */
	public static ASystem getASystem() {
		return aData.aSystem;
	}

	public static PlayerAllData getPlayerAllData(int clientId) {
		return PlayerCache.getPlayerByClientId(clientId).getPlayerAllData();
	}

	public static SystemAllData getSystemAllData() {
		return aData.aSystem.getSystemAllData();
	}

	public static PlanetAllData getPlanetAllData(int planetId) {
		return PlanetCache.getPlanetById(planetId).getPlanetAllData();
	}

}
