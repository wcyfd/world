package org.aimfd.world;

import org.aimfd.world.planet.Planet;
import org.aimfd.world.planet.PlanetAllData;
import org.aimfd.world.player.Player;
import org.aimfd.world.player.PlayerAllData;
import org.aimfd.world.system.ASystem;
import org.aimfd.world.system.SystemAllData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AData {

	private static final Logger logger = LoggerFactory.getLogger(AData.class);
	private static AData aData = new AData();

	private ASystem aSystem;

	protected static void initPlayerData(int maxCount) {
		for (int clientId = 0; clientId < maxCount; clientId++) {
			Player player = new Player(clientId);
			PlayerCache.addPlayer(player);
		}

		logger.info("初始化玩家内存完毕 count={}", maxCount);
	}

	protected static void playerModuleInit(int maxCount) {
		// 玩家初始化
		for (int clientId = 0; clientId < maxCount; clientId++) {
			Player player = PlayerCache.getPlayerByClientId(clientId);
			player.init();
		}

		logger.info("玩家业务模块绑定完成");
	}

	protected static void initPlanetData(int maxCount) {
		for (int planetId = 0; planetId < maxCount; planetId++) {
			Planet planet = new Planet(planetId);
			PlanetCache.addPlanet(planet);
		}

		logger.info("初始化场景内存完毕 count={}", maxCount);
	}

	protected static void planetModuleInit(int maxCount) {
		// 星球初始化
		for (int planetId = 0; planetId < maxCount; planetId++) {
			Planet planet = PlanetCache.getPlanetById(planetId);
			planet.init();
		}
		logger.info("场景业务模块绑定完成");
	}

	protected static void initSystemData() {
		aData.aSystem = new ASystem();
		logger.info("初始化系统内存完毕 ");
	}

	protected static void systemModuleInit() {
		// 系统初始化
		AData.getASystem().init();
		logger.info("系统业务模块绑定完成");
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

	public static PlayerAllData getPlayerAllData(String account) {
		Player player = PlayerCache.getPlayerByAccount(account);
		if (player == null) {
			return null;
		}

		return player.getPlayerAllData();
	}

	public static SystemAllData getSystemAllData() {
		return aData.aSystem.getSystemAllData();
	}

	public static PlanetAllData getPlanetAllData(int planetId) {
		return PlanetCache.getPlanetById(planetId).getPlanetAllData();
	}

}
