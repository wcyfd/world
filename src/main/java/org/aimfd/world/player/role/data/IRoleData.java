package org.aimfd.world.player.role.data;

import org.aimfd.world.player.role.data.unit.IRoleCoordUnitData;

public interface IRoleData {
	/**
	 * 设置角色id
	 * 
	 * @param roleId
	 */
	void setRoleId(int roleId);

	/**
	 * 获得角色id
	 * 
	 * @return
	 */
	int getRoleId();

	/**
	 * 设置名字
	 * 
	 * @param name
	 */
	void setName(String name);

	/**
	 * 获得名字
	 * 
	 * @return
	 */
	String getName();

	/**
	 * 设置账号
	 * 
	 * @param account
	 */
	void setAccount(String account);

	/**
	 * 获得账号
	 * 
	 * @return
	 */
	String getAccount();

	/**
	 * 获得角色坐标
	 * 
	 * @return
	 */
	IRoleCoordUnitData getCoord();
}
