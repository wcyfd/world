package org.aimfd.world.planet.terrain4.data;

import java.util.Map;

import org.aimfd.base.IDataJSONCodec;
import org.aimfd.world.planet.terrain4.data.unit.ITerrain4UnitData;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Terrain4DataCodec implements IDataJSONCodec {

	protected Map<Integer, ITerrain4UnitData> unitMap;
	protected int count;
	protected int width;
	protected int height;

	@Override
	public JSONObject encode() {
		JSONObject json = new JSONObject();
		json.put("width", width);
		json.put("height", height);
		JSONArray unitDataArray = new JSONArray();
		json.put("unitDataMap", unitDataArray);
		for (int i = 0; i < count; i++) {
			IDataJSONCodec unitDataCodec = (IDataJSONCodec) unitMap.get(i);
			unitDataArray.add(unitDataCodec.encode());
		}
		return json;
	}

	@Override
	public void decode(JSONObject source) {
		this.width = source.getIntValue("width");
		this.height = source.getIntValue("height");
		JSONArray array = source.getJSONArray("unitDataMap");
		this.count = array.size();

		for (int i = 0; i < count; i++) {
			JSONObject unitDataJson = array.getJSONObject(i);
			IDataJSONCodec unitDataCodec = (IDataJSONCodec) unitMap.get(i);
			unitDataCodec.decode(unitDataJson);
		}
	}

}
