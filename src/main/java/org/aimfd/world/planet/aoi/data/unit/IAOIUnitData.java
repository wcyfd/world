package org.aimfd.world.planet.aoi.data.unit;

import java.util.Map;

import org.aimfd.world.planet.role.data.IRoleData;

/**
 * aoi单元
 * 
 * @author AIM
 *
 */
public interface IAOIUnitData {
	/**
	 * 获得角色表
	 * 
	 * @return
	 */
	Map<String, IRoleData> getRoleDataMap();

	/**
	 * 设置id
	 * 
	 * @param id
	 */
	void setId(int id);

	/**
	 * 获得id
	 * 
	 * @return
	 */
	int getId();
}
