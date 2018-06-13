package org.aimfd.world.planet.terrain;

public interface ITerrainPublic {
	/**
	 * 设置地图大小
	 * 
	 * @param height
	 * @param width
	 */
	void setSize(int height, int width);

	/**
	 * 
	 * @param id
	 * @param enterpriseId
	 * @param type
	 */
	void putTerrain(int id, int enterpriseId, int type);
}
