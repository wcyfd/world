package org.aimfd.world.planet.role.data;

/**
 * 角色数据
 * 
 * @author AIM
 *
 */
public interface IRoleData {
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

}
