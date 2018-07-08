package org.aimfd.world.planet.aoi.data.grid;

import java.util.HashMap;
import java.util.Map;

import org.aimfd.base.IData;
import org.aimfd.world.planet.aoi.data.grid.unit.IAOIGridUnitData;

/**
 * aoi总集
 * 
 * @author AIM
 *
 */
public class AOIGridData extends AOIGridDataCodec implements IData, IAOIGridData {

	public AOIGridData() {
		this.aoiUnitMap = new HashMap<>();
	}

	@Override
	public Map<Integer, IAOIGridUnitData> getAOIUnitDataMap() {
		return aoiUnitMap;
	}

	@Override
	public void resetData() {
		aoiUnitMap.clear();
	}

}
