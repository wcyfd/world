package org.aimfd.world.planet.aoi.data;

import java.util.Map;

import org.aimfd.base.IDataJSONCodec;
import org.aimfd.world.planet.aoi.data.unit.IAOIUnitData;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class AOIDataCodec implements IDataJSONCodec {

	protected Map<Integer, IAOIUnitData> aoiUnitDataMap;

	@Override
	public JSONObject encode() {
		JSONObject json = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		json.put("aoiUnitDataMap", jsonArray);
		for (IAOIUnitData unitData : aoiUnitDataMap.values()) {
			IDataJSONCodec unitDataJSONCodec = (IDataJSONCodec) unitData;
			jsonArray.add(unitDataJSONCodec.encode());
		}
		return json;
	}

	@Override
	public void decode(JSONObject source) {
		// 不允许客户端重写
	}

}
