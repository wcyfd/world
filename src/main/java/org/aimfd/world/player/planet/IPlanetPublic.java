package org.aimfd.world.player.planet;

public interface IPlanetPublic {
	/**
	 * 放置瓦片
	 * 
	 * @param type
	 */
	public void putTerrain(int id, int type);

	/**
	 * 全局坐标移动
	 * 
	 * @param x
	 * @param y
	 */
	void move(int x, int y);
}
