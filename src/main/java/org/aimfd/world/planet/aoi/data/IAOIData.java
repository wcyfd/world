package org.aimfd.world.planet.aoi.data;

import org.aimfd.world.planet.aoi.data.unit.IAOIUnitData;

public interface IAOIData {
	/**
	 * 获得一块AOI区域
	 * 
	 * @param id
	 * @return
	 */
	IAOIUnitData getAOIUnitData(int id);

	/**
	 * 添加一块AOI区域
	 * 
	 * @param unitData
	 */
	void addAOIUnitData(IAOIUnitData unitData);
}
