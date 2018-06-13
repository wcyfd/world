package org.aimfd.world.system.room;

/**
 * 房间接口
 * 
 * @author AIM
 *
 */
public interface ISystemRoomPublic {

	/**
	 * 注册
	 * 
	 * @param account
	 * @param planetId
	 */
	void regist(String account, int planetId);

	/**
	 * 注销
	 * 
	 * @param account
	 */
	void unregist(String account);

	/**
	 * 是否在房间
	 * 
	 * @param account
	 * @return
	 */
	boolean atRoom(String account);

	/**
	 * 获得账号所在的星球id
	 * 
	 * @param account
	 * @return
	 */
	int getPlanetId(String account);
}
