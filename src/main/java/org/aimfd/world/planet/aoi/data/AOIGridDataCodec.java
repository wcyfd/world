package org.aimfd.world.planet.aoi.data;

import java.util.Map;

import org.aimfd.base.IDataJSONCodec;
import org.aimfd.world.planet.aoi.data.unit.IAOIGridUnitData;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class AOIGridDataCodec implements IDataJSONCodec {
	protected Map<Integer, IAOIGridUnitData> aoiUnitMap;

	@Override
	public JSONObject encode() {
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
		json.put("aoiUnitMap", array);
		for (IAOIGridUnitData unitData : aoiUnitMap.values()) {
			IDataJSONCodec unitDataCodec = (IDataJSONCodec) unitData;
			array.add(unitDataCodec.encode());
		}
		return json;
	}

	@Override
	public void decode(JSONObject source) {

	}

}
