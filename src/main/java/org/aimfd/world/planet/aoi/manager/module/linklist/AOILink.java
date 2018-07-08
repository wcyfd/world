package org.aimfd.world.planet.aoi.manager.module.linklist;

import org.aimfd.world.planet.Planet;
import org.aimfd.world.player.role.data.IRoleData;

public class AOILink {
	private AOILinkEnterModule enterModule;
	private AOILinkMoveModule moveModule;
	private AOILinkLeaveModule leaveModule;

	public void init(Planet planet) {
		enterModule = new AOILinkEnterModule(planet);
		moveModule = new AOILinkMoveModule(planet);
		leaveModule = new AOILinkLeaveModule(planet);
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
