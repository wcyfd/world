package org.aimfd.world.planet.roles.manager.module;

import org.aimfd.world.planet.Planet;
import org.aimfd.world.planet.PlanetICenter;
import org.aimfd.world.planet.aoi.IAOIPublic;
import org.aimfd.world.player.role.data.IRoleData;

public class RoleMoveModule {
	private IAOIPublic aoiPublic;

	public RoleMoveModule(Planet planet) {
		aoiPublic = PlanetICenter.getInterface(planet.getPlanetId(), IAOIPublic.class);
	}

	/**
	 * 移动
	 * 
	 * @param roleId
	 * @param destX
	 * @param destY
	 */
	public void move(IRoleData roleData, int destX, int destY) {
		int srcX = roleData.getCoord().getX();
		int srcY = roleData.getCoord().getY();

		roleData.getCoord().setX(destX);
		roleData.getCoord().setY(destY);

		// aoi视野内通知移动
		aoiPublic.move(roleData.getRoleId(), srcX, srcY, destX, destY);
	}
}
