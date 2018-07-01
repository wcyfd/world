package org.aimfd.world.planet.aoi.manager;

import org.aimfd.world.planet.PlanetManager;
import org.aimfd.world.planet.aoi.IAOIPublic;
import org.aimfd.world.planet.aoi.manager.module.AOIEnterModule;
import org.aimfd.world.planet.aoi.manager.module.AOILeaveModule;
import org.aimfd.world.planet.aoi.manager.module.AOIMoveModule;
import org.aimfd.world.player.role.data.IRoleData;

/**
 * AOI实现
 * 
 * @author AIM
 *
 */
public class PlanetAOIManager extends PlanetManager implements IAOIPublic {

	private AOIEnterModule enterModule;
	private AOILeaveModule leaveModule;
	private AOIMoveModule moveModule;

	@Override
	public void init() {
		enterModule = new AOIEnterModule(planet);
		leaveModule = new AOILeaveModule(planet);
		moveModule = new AOIMoveModule(planet);
	}

	@Override
	public void enter(IRoleData roleData, int x, int y) {
		enterModule.enter(roleData, x, y);
	}

	@Override
	public void leave(int roleId) {
		leaveModule.leave(roleId);
	}

	@Override
	public void move(int roleId, int srcX, int srcY, int destX, int destY) {
		moveModule.move(roleId, srcX, srcY, destX, destY);
	}

}
