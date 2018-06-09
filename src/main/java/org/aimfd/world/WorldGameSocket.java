package org.aimfd.world;

import org.aimfd.base.GameSocket;
import org.aimfd.world.handler.AccountHandler;
import org.aimfd.world.handler.PlanetHandler;

public class WorldGameSocket extends GameSocket {

	private int maxPlayerCount;
	private int maxPlanetCount;

	public WorldGameSocket() {
		super();
		maxPlayerCount = 10;
		maxPlanetCount = 5;
		maxConnectionCount = maxPlayerCount;
	}

	@Override
	protected void initData() {

		AData.initPlayerData(maxPlayerCount);
		AData.initSystemData();
		AData.initPlanetData(maxPlanetCount);
	}

	@Override
	protected void initSpecial() {

		// 玩家初始化
		AData.playerModuleInit(maxPlayerCount);

		// 星球初始化
		AData.planetModuleInit(maxPlanetCount);

		// 系统初始化
		AData.systemModuleInit();

		// 注册通信接口
		registHandlers();
	}

	private void registHandlers() {
		registHandler(AccountHandler.class);
		registHandler(PlanetHandler.class);
	}

}
