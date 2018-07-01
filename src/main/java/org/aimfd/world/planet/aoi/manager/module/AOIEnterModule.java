package org.aimfd.world.planet.aoi.manager.module;

import org.aimfd.world.planet.Planet;
import org.aimfd.world.planet.roles.data.IRolesData;
import org.aimfd.world.player.role.data.IRoleData;

public class AOIEnterModule {
	
	private IRolesData rolesData;

	public AOIEnterModule(Planet planet) {
		rolesData = planet.getPlanetAllData().getPlanetData().getRolesData();
	}

	public void enter(IRoleData roleData, int x, int y) {

	}

}
