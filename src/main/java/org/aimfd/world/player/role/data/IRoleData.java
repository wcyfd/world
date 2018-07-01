package org.aimfd.world.player.role.data;

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

	void setX(int x);

	int getX();

	void setY(int y);

	int getY();
}
