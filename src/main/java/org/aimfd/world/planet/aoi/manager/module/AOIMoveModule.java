package org.aimfd.world.planet.aoi.manager.module;

import org.aimfd.world.planet.Planet;
import org.aimfd.world.planet.roles.data.IRolesData;
import org.aimfd.world.player.role.data.IRoleData;

/**
 * 移动
 * 
 * @author AIM
 *
 */
public class AOIMoveModule {
	private IRolesData rolesData;

	public AOIMoveModule(Planet planet) {
		rolesData = planet.getPlanetAllData().getPlanetData().getRolesData();
	}

	/**
	 * 移动
	 * 
	 * @param roleId
	 * @param srcX
	 *            旧位置x
	 * @param srcY
	 *            旧位置y
	 * @param destX
	 *            新位置x
	 * @param destY
	 *            新位置y
	 */
	public void move(int roleId, int srcX, int srcY, int destX, int destY) {
		IRoleData roleData = rolesData.getRoleData(roleId);
		
	}
}
