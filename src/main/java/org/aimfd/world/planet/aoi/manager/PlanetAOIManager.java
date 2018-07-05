package org.aimfd.world.planet.aoi.manager;

import org.aimfd.world.planet.PlanetManager;
import org.aimfd.world.planet.aoi.IAOIPublic;
import org.aimfd.world.planet.aoi.manager.module.grid.AOIGrid;
import org.aimfd.world.player.role.data.IRoleData;

/**
 * AOI实现
 * 
 * @author AIM
 *
 */
public class PlanetAOIManager extends PlanetManager implements IAOIPublic {

	private AOIGrid aoiGrid;

	@Override
	public void init() {
		aoiGrid = new AOIGrid();
		aoiGrid.init(planet);
	}

	@Override
	public void enter(IRoleData roleData, int x, int y) {
		aoiGrid.enter(roleData, x, y);
	}

	@Override
	public void leave(int roleId, int x, int y) {
		aoiGrid.leave(roleId, x, y);
	}

	@Override
	public void move(int roleId, int srcX, int srcY, int destX, int destY) {
		aoiGrid.move(roleId, srcX, srcY, destX, destY);
	}

}
