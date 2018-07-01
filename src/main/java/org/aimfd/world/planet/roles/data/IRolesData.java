package org.aimfd.world.planet.roles.data;

import org.aimfd.world.player.role.data.IRoleData;

public interface IRolesData {
	/**
	 * 获得角色
	 * 
	 * @param id
	 * @return
	 */
	IRoleData getRoleData(int id);

	/**
	 * 添加角色
	 * 
	 * @param unitData
	 */
	void addRoleData(IRoleData unitData);

	/**
	 * 删除角色
	 * 
	 * @param roleId
	 */
	void removeRoleData(int roleId);
}
