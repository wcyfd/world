package org.aimfd.world.player.planet.manager.module;

import org.aimfd.world.planet.PlanetICenter;
import org.aimfd.world.planet.roles.IRolesPublic;
import org.aimfd.world.player.Player;
import org.aimfd.world.player.role.data.IRoleData;

/**
 * 玩家加入道场景的请求
 * 
 * @author AIM
 *
 */
public class PlayerJoinModule {

	private IRoleData roleData;

	public PlayerJoinModule(Player player) {
		roleData = player.getPlayerAllData().getPeaceData().getRoleData();
	}

	public void join(int planetId) {
		IRolesPublic rolesPublic = PlanetICenter.getInterface(planetId, IRolesPublic.class);
		rolesPublic.join(roleData, 0, 0);
	}
}
