package org.aimfd.world.planet.aoi.data;

import java.util.HashMap;

import org.aimfd.base.IData;
import org.aimfd.world.planet.aoi.data.unit.IAOIUnitData;

/**
 * AOI总集
 * 
 * @author AIM
 *
 */
public class AOIData extends AOIDataCodec implements IData, IAOIData {

	public AOIData() {
		this.aoiUnitDataMap = new HashMap<>();
	}

	@Override
	public IAOIUnitData getAOIUnitData(int id) {
		return aoiUnitDataMap.get(id);
	}

	@Override
	public void resetData() {
		aoiUnitDataMap.clear();
	}

}
