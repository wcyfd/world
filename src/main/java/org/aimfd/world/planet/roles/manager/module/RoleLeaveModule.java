package org.aimfd.world.planet.roles.manager.module;

import org.aimfd.world.planet.Planet;
import org.aimfd.world.planet.PlanetICenter;
import org.aimfd.world.planet.aoi.IAOIPublic;
import org.aimfd.world.planet.roles.data.IRolesData;
import org.aimfd.world.player.role.data.IRoleData;

public class RoleLeaveModule {
	private IRolesData rolesData;
	private IAOIPublic aoiPublic;

	public RoleLeaveModule(Planet planet) {
		rolesData = planet.getPlanetAllData().getPlanetData().getRolesData();
		aoiPublic = PlanetICenter.getInterface(planet.getPlanetId(), IAOIPublic.class);
	}

	public void leave(IRoleData roleData) {
		int roleId = roleData.getRoleId();
		int x = roleData.getX();
		int y = roleData.getY();

		rolesData.removeRoleData(roleId);
		// aoi视野内通知离开
		aoiPublic.leave(roleId, x, y);
	}
}
