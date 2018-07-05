package org.aimfd.world.planet.aoi.manager.module.grid;

import java.util.Set;

import org.aimfd.world.handler.PlanetHandler;
import org.aimfd.world.planet.Planet;

public class AOIGridLeaveModule {
	private AOIGridLocationModule locationModule;

	public AOIGridLeaveModule(Planet planet, AOIGridLocationModule locationModule) {
		this.locationModule = locationModule;
	}

	public void leave(int roleId, int x, int y) {
		Set<Integer> clientIdSet = locationModule.getRangeClientId(x, y);

		// 通知有人物进入了场景
		PlanetHandler.sendLeaveRole(clientIdSet, roleId);
	}

}
