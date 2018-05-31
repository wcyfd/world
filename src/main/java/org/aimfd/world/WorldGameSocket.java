package org.aimfd.world;

import org.aimfd.base.GameSocket;
import org.aimfd.world.handler.AccountHandler;
import org.aimfd.world.handler.PlanetHandler;
import org.aimfd.world.planet.Planet;
import org.aimfd.world.player.Player;

public class WorldGameSocket extends GameSocket {

	private int maxPlayerCount;
	private int maxPlanetCount;

	public WorldGameSocket() {
		super();
	}

	@Override
	protected void initData() {
		maxPlayerCount = 10;
		maxPlanetCount = 5;

		AData.initPlayerData(maxPlayerCount);
		AData.initSystemData();
		AData.initPlanetData(maxPlanetCount);
	}

	@Override
	protected void initSpecial() {

		// 玩家初始化
		for (int clientId = 0; clientId < maxPlayerCount; clientId++) {
			Player player = PlayerCache.getPlayerByClientId(clientId);
			player.init();
		}

		// 星球初始化
		for (int planetId = 0; planetId < maxPlanetCount; planetId++) {
			Planet planet = PlanetCache.getPlanetById(planetId);
			planet.init();
		}

		// 系统初始化
		AData.getASystem().init();

		// 注册通信接口
		registHandlers();
	}

	private void registHandlers() {
		registHandler(AccountHandler.class);
		registHandler(PlanetHandler.class);
	}

}
