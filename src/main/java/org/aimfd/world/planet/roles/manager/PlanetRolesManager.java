package org.aimfd.world.planet.roles.manager;

import org.aimfd.world.planet.PlanetManager;
import org.aimfd.world.planet.roles.IRolesPublic;
import org.aimfd.world.planet.roles.manager.module.RoleEnterModule;
import org.aimfd.world.player.role.data.IRoleData;

public class PlanetRolesManager extends PlanetManager implements IRolesPublic {

	private RoleEnterModule roleEnterModule;
	@Override
	public void init() {
		roleEnterModule = new RoleEnterModule(planet);
	}

	@Override
	public void join(IRoleData roleData, int x, int y) {
		roleEnterModule.join(roleData, x, y);
	}

	@Override
	public void leave(IRoleData roleData) {
		
	}

}
