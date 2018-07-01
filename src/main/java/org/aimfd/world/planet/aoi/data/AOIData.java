package org.aimfd.world.planet.aoi.data;

import java.util.HashMap;

import org.aimfd.base.IData;
import org.aimfd.world.planet.aoi.data.unit.IAOIUnitData;

/**
 * aoi总集
 * 
 * @author AIM
 *
 */
public class AOIData extends AOIDataCodec implements IData, IAOIData {

	public AOIData() {
		this.aoiUnitMap = new HashMap<>();
	}

	@Override
	public IAOIUnitData getAOIUnitData(int id) {
		return aoiUnitMap.get(id);
	}

	@Override
	public void addAOIUnitData(IAOIUnitData unitData) {
		aoiUnitMap.put(unitData.getId(), unitData);
	}

	@Override
	public void resetData() {
		aoiUnitMap.clear();
	}

}
