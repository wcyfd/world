package org.aimfd.world.planet.terrain.data;

import java.util.Map;

import org.aimfd.base.IDataJSONCodec;
import org.aimfd.world.planet.terrain.data.unit.ITerrainUnitData;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class TerrainDataCodec implements IDataJSONCodec {

	protected int height;
	protected int width;
	protected int terrainUnitCount;
	protected Map<Integer, ITerrainUnitData> unitDataMap;

	@Override
	public JSONObject encode() {
		JSONObject json = new JSONObject();
		json.put("width", width);
		json.put("height", height);
		json.put("terrainUnitCount", terrainUnitCount);
		JSONArray unitDataMapJson = new JSONArray();
		json.put("unitDataMap", unitDataMapJson);
		for (int i = 0; i < terrainUnitCount; i++) {
			ITerrainUnitData unitData = unitDataMap.get(i);
			unitDataMapJson.add(((IDataJSONCodec) unitData).encode());
		}
		return json;
	}

	@Override
	public void decode(JSONObject source) {
		this.width = source.getInteger("width");
		this.height = source.getInteger("height");
		this.terrainUnitCount = source.getInteger("terrainUnitCount");

		JSONArray unitDataMapJson = source.getJSONArray("unitDataMap");
		for (int i = 0; i < terrainUnitCount; i++) {
			JSONObject unitDataJson = unitDataMapJson.getJSONObject(i);
			IDataJSONCodec codec = (IDataJSONCodec) this.unitDataMap.get(i);
			codec.decode(unitDataJson);
		}
	}

}
