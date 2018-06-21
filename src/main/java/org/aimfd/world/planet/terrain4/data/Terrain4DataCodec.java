package org.aimfd.world.planet.terrain4.data;

import java.util.Map;

import org.aimfd.base.IDataJSONCodec;
import org.aimfd.world.planet.terrain4.data.unit.ITerrain4UnitData;

import com.alibaba.fastjson.JSONObject;

public class Terrain4DataCodec implements IDataJSONCodec {

	protected Map<Integer, ITerrain4UnitData> unitMap;

	@Override
	public JSONObject encode() {
		return null;
	}

	@Override
	public void decode(JSONObject source) {

	}

}
