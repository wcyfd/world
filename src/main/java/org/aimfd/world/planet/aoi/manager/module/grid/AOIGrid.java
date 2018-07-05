package org.aimfd.world.planet.aoi.manager.module.grid;

import org.aimfd.world.planet.Planet;
import org.aimfd.world.player.role.data.IRoleData;

public class AOIGrid {
	private AOIGridEnterModule enterModule;
	private AOIGridLeaveModule leaveModule;
	private AOIGridMoveModule moveModule;
	private AOIGridLocationModule locationModule;

	public void init(Planet planet) {
		locationModule = new AOIGridLocationModule(planet);
		enterModule = new AOIGridEnterModule(planet, locationModule);
		leaveModule = new AOIGridLeaveModule(planet, locationModule);
		moveModule = new AOIGridMoveModule(planet, locationModule);
	}

	public void enter(IRoleData roleData, int x, int y) {
		enterModule.enter(roleData, x, y);
	}

	public void leave(int roleId, int x, int y) {
		leaveModule.leave(roleId, x, y);
	}

	public void move(int roleId, int srcX, int srcY, int destX, int destY) {
		moveModule.move(roleId, srcX, srcY, destX, destY);
	}
}
