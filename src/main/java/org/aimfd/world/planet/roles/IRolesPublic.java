package org.aimfd.world.planet.roles;

import org.aimfd.world.player.role.data.IRoleData;

/**
 * 角色接口
 * 
 * @author AIM
 *
 */
public interface IRolesPublic {
	/**
	 * 进入场景
	 * 
	 * @param roleData
	 * @param x
	 * @param y
	 */
	void join(IRoleData roleData, int x, int y);

	/**
	 * 离开场景
	 * 
	 * @param roleData
	 */
	void leave(IRoleData roleData);
}
