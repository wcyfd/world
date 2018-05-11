package org.aimfd.world.planet.enterprise.data;

import java.util.Map;

import org.aimfd.world.IDataCodec;
import org.aimfd.world.planet.enterprise.data.unit.EnterpriseUnitData;

public class EnterpriseDataCodec implements IDataCodec<String, String> {

	protected int enterpriseCount;
	protected Map<Integer, EnterpriseUnitData> enterpriseUnitDataMap;
	protected Map<String, Integer> enterpriseAccountMap;

	@Override
	public String encode() {
		return null;
	}

	@Override
	public void decode(String input) {

	}

}
