package org.aimfd.world.planet.aoi.data.grid;

import java.util.Map;

import org.aimfd.world.planet.aoi.data.grid.unit.IAOIGridUnitData;

public interface IAOIGridData {
	/**
	 * 获取AOI区域表
	 * 
	 * @return
	 */
	Map<Integer, IAOIGridUnitData> getAOIUnitDataMap();
}
