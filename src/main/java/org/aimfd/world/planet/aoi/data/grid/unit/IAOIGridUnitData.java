package org.aimfd.world.planet.aoi.data.grid.unit;

import java.util.Set;

public interface IAOIGridUnitData {
	/**
	 * 设置id
	 * 
	 * @param id
	 */
	void setId(int id);

	/**
	 * 获取id
	 * 
	 * @return
	 */
	int getId();

	/**
	 * 设置左上角起始点X坐标
	 * 
	 * @param x
	 */
	void setX(int x);

	/**
	 * 获得左上角X坐标
	 * 
	 * @return
	 */
	int getX();

	/**
	 * 设置左上角Y坐标
	 * 
	 * @param y
	 */
	void setY(int y);

	/**
	 * 获得左上角Y坐标
	 * 
	 * @return
	 */
	int getY();

	/**
	 * 设置宽度
	 * 
	 * @param width
	 */
	void setWidth(int width);

	/**
	 * 获得宽度
	 * 
	 * @return
	 */
	int getWidth();

	/**
	 * 设置长度
	 * 
	 * @param height
	 */
	void setHeight(int height);

	/**
	 * 获得长度
	 * 
	 * @return
	 */
	int getHeight();

	/**
	 * 获取所有角色id
	 * 
	 * @return
	 */
	Set<Integer> getRoleIdSet();
}
