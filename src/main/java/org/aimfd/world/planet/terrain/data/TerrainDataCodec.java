package org.aimfd.world.planet.terrain.data;

import java.util.Map;

import org.aimfd.base.IDataJSONCodec;
import org.aimfd.world.planet.terrain.data.unit.TerrainUnitData;

import com.alibaba.fastjson.JSONObject;

public class TerrainDataCodec implements IDataJSONCodec {

	protected int height;
	protected int width;
	protected Map<Integer, TerrainUnitData> unitDataMap;

	@Override
	public JSONObject encode() {
		JSONObject json = new JSONObject();
		return null;
	}

	@Override
	public void decode(JSONObject source) {
		// TODO Auto-generated method stub

	}

}
