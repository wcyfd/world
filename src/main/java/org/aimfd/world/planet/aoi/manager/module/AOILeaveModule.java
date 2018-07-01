package org.aimfd.world.planet.aoi.manager.module;

import org.aimfd.world.planet.Planet;
import org.aimfd.world.planet.roles.data.IRolesData;

public class AOILeaveModule {
	private IRolesData rolesData;

	public AOILeaveModule(Planet planet) {
		rolesData = planet.getPlanetAllData().getPlanetData().getRolesData();
	}

	public void leave(int roleId) {
		rolesData.removeRoleData(roleId);
	}

}
