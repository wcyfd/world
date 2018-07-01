package org.aimfd.world.player.role.manager.module;

import org.aimfd.world.player.Player;
import org.aimfd.world.player.role.data.IRoleData;

public class RoleModule {
	private IRoleData roleData;

	public RoleModule(Player player) {
		roleData = player.getPlayerAllData().getPeaceData().getRoleData();
	}

	public IRoleData getRoleData() {
		return roleData;
	}
}
