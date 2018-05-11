package org.aimfd.world.planet.terrain.data;

import org.aimfd.world.planet.terrain.data.unit.ITerrainUnitData;

/**
 * 地图
 * 
 * @author AIM
 *
 */
public interface ITerrainData {
	/**
	 * 获取地图板块单元
	 * 
	 * @param id
	 *            板块id
	 * @return
	 */
	ITerrainUnitData getTerrainUnitData(int id);

	/**
	 * 设置地图的长度
	 * 
	 * @param size
	 */
	void setTerrainHeight(int height);

	/**
	 * 获取地图长度
	 * 
	 * @return
	 */
	int getTerrainHeight();

	/**
	 * 设置地图宽度
	 * 
	 * @param width
	 */
	void setTerrainWidth(int width);

	/**
	 * 获得地图宽度
	 * 
	 * @return
	 */
	int getTerrainWidth();

	/**
	 * 获得地图板块数量
	 * 
	 * @return
	 */
	int getTerrainUnitCount();

	/**
	 * 设置地图板块数量
	 * 
	 * @param count
	 */
	void setTerrainUnitCount(int count);

}
