package org.aimfd.world.planet.aoi.data;

import org.aimfd.world.planet.aoi.data.unit.IAOIUnitData;

/**
 * aoi总集合
 * 
 * @author AIM
 *
 */
public interface IAOIData {
	/**
	 * 根据id获取AOI单元
	 * 
	 * @param id
	 * @return
	 */
	IAOIUnitData getAOIUnitData(int id);
}
