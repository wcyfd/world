package org.aimfd.world.player.role.manager.module;

import org.aimfd.world.handler.PlanetHandler;
import org.aimfd.world.planet.PlanetICenter;
import org.aimfd.world.planet.roles.IRolesPublic;
import org.aimfd.world.player.Player;
import org.aimfd.world.player.role.data.IRoleData;

public class RoleModule {
	private IRoleData roleData;
	private Player player;

	public RoleModule(Player player) {
		roleData = player.getPlayerAllData().getPeaceData().getRoleData();
		this.player = player;
	}

	public void move(int x, int y) {

		PlanetHandler.responseMove(player.getClientId());

		// 默认现在就一个地区
		IRolesPublic rolesPublic = PlanetICenter.getInterface(0, IRolesPublic.class);
		rolesPublic.move(roleData, x, y);
	}
}
