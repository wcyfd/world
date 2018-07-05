package org.aimfd.world.planet.aoi.data;

import java.util.Map;

import org.aimfd.world.planet.aoi.data.unit.IAOIGridUnitData;

public interface IAOIGridData {
	/**
	 * 获取AOI区域表
	 * 
	 * @return
	 */
	Map<Integer, IAOIGridUnitData> getAOIUnitDataMap();
}
