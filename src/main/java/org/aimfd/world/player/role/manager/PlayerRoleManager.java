package org.aimfd.world.player.role.manager;

import org.aimfd.world.player.PlayerManager;
import org.aimfd.world.player.role.IRolePublic;
import org.aimfd.world.player.role.data.IRoleData;
import org.aimfd.world.player.role.manager.module.RoleModule;

public class PlayerRoleManager extends PlayerManager implements IRolePublic {

	private RoleModule roleModule;

	@Override
	public void init() {
		roleModule = new RoleModule(player);
	}

	@Override
	public IRoleData getRoleData() {
		return roleModule.getRoleData();
	}

}
