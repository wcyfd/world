package org.aimfd.world.planet.aoi.manager.module.grid;

import java.util.Set;

import org.aimfd.world.handler.PlanetHandler;
import org.aimfd.world.planet.Planet;
import org.aimfd.world.player.role.data.IRoleData;

public class AOIGridEnterModule {

	private AOIGridLocationModule locationModule;

	public AOIGridEnterModule(Planet planet, AOIGridLocationModule locationModule) {
		this.locationModule = locationModule;
	}

	/**
	 * 进入场景
	 * 
	 * @param roleData
	 * @param x
	 * @param y
	 */
	public void enter(IRoleData roleData, int x, int y) {

		// 获取该坐标的所有地图
		Set<Integer> clientIdSet = locationModule.getRangeClientId(x, y);

		// 通知有人物进入了场景
		PlanetHandler.sendEnterRole(clientIdSet, roleData);
	}

}
