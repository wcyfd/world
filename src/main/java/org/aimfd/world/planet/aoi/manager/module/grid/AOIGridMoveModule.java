package org.aimfd.world.planet.aoi.manager.module.grid;

import java.util.HashSet;
import java.util.Set;

import org.aimfd.world.handler.PlanetHandler;
import org.aimfd.world.planet.Planet;
import org.aimfd.world.planet.roles.data.IRolesData;
import org.aimfd.world.player.role.data.IRoleData;

/**
 * 移动
 * 
 * @author AIM
 *
 */
public class AOIGridMoveModule {
	private IRolesData rolesData;
	private AOIGridLocationModule locationModule;

	public AOIGridMoveModule(Planet planet, AOIGridLocationModule module) {
		rolesData = planet.getPlanetAllData().getPlanetData().getRolesData();
		this.locationModule = module;
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
		Set<Integer> srcClientIds = locationModule.getRangeClientId(srcX, srcY);
		Set<Integer> destClientIds = locationModule.getRangeClientId(destX, destY);

		// 交集说明是移动
		Set<Integer> moveClientIds = retain(srcClientIds, destClientIds);

		// 失去视野
		srcClientIds.removeAll(moveClientIds);

		// 获取视野
		destClientIds.removeAll(moveClientIds);

		PlanetHandler.sendMoveRole(moveClientIds, roleId);
		PlanetHandler.sendLeaveRole(srcClientIds, roleId);
		PlanetHandler.sendEnterRole(destClientIds, roleData);
	}

	/**
	 * 获得交集
	 * 
	 * @param srcClientIds
	 * @param destClientIds
	 * @return
	 */
	private Set<Integer> retain(Set<Integer> srcClientIds, Set<Integer> destClientIds) {
		Set<Integer> moveClientIdSet = new HashSet<>();
		moveClientIdSet.addAll(srcClientIds);
		moveClientIdSet.retainAll(destClientIds);
		return moveClientIdSet;
	}
}
