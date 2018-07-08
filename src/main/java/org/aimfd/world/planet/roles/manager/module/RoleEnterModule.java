package org.aimfd.world.planet.roles.manager.module;

import org.aimfd.world.planet.Planet;
import org.aimfd.world.planet.PlanetICenter;
import org.aimfd.world.planet.aoi.IAOIPublic;
import org.aimfd.world.planet.roles.data.IRolesData;
import org.aimfd.world.player.role.data.IRoleData;

public class RoleEnterModule {
	private IRolesData rolesData;
	private IAOIPublic aoiPublic;

	public RoleEnterModule(Planet planet) {
		rolesData = planet.getPlanetAllData().getPlanetData().getRolesData();
		aoiPublic = PlanetICenter.getInterface(planet.getPlanetId(), IAOIPublic.class);
	}

	/**
	 * 加入
	 * 
	 * @param roleData
	 * @param x
	 * @param y
	 */
	public void join(IRoleData roleData, int x, int y) {
		IRoleData unitData = rolesData.getRoleData(roleData.getRoleId());
		if (unitData == null) {
			unitData = roleData;
			rolesData.addRoleData(roleData);
		}
		unitData.getCoord().setX(x);
		unitData.getCoord().setY(y);
		// 向视野内所有玩家发送进场通知
		aoiPublic.enter(roleData, x, y);
	}

}
