package org.aimfd.world.planet.roles.manager;

import org.aimfd.world.planet.PlanetManager;
import org.aimfd.world.planet.roles.IRolesPublic;
import org.aimfd.world.planet.roles.manager.module.RoleEnterModule;
import org.aimfd.world.planet.roles.manager.module.RoleLeaveModule;
import org.aimfd.world.planet.roles.manager.module.RoleMoveModule;
import org.aimfd.world.player.role.data.IRoleData;

public class PlanetRolesManager extends PlanetManager implements IRolesPublic {

	private RoleEnterModule roleEnterModule;
	private RoleLeaveModule roleLeaveModule;
	private RoleMoveModule roleMoveModule;

	@Override
	public void init() {
		roleEnterModule = new RoleEnterModule(planet);
		roleLeaveModule = new RoleLeaveModule(planet);
		roleMoveModule = new RoleMoveModule(planet);
	}

	@Override
	public void join(IRoleData roleData, int x, int y) {
		roleEnterModule.join(roleData, x, y);
	}

	@Override
	public void leave(IRoleData roleData) {
		roleLeaveModule.leave(roleData);
	}

	@Override
	public void move(IRoleData roleData, int destX, int destY) {
		roleMoveModule.move(roleData, destX, destY);
	}

}
