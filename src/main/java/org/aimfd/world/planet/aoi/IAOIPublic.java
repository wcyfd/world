package org.aimfd.world.planet.aoi;

import org.aimfd.world.player.role.data.IRoleData;

/**
 * Area of Interest 公共方法
 * 
 * @author AIM
 *
 */
public interface IAOIPublic {
	/**
	 * 进入视野通知
	 * 
	 * @param account
	 * @param x
	 * @param y
	 */
	void enter(IRoleData roleData, int x, int y);

	/**
	 * 退出视野通知
	 * 
	 * @param account
	 */
	void leave(int roleId, int x, int y);

	/**
	 * 移动通知
	 * 
	 * @param account
	 * @param srcX
	 * @param srcY
	 * @param destX
	 * @param destY
	 */
	void move(int roleId, int srcX, int srcY, int destX, int destY);
}
