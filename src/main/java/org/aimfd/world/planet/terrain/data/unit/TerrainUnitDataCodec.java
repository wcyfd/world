package org.aimfd.world.planet.terrain.data.unit;

import org.aimfd.base.IDataJSONCodec;

import com.alibaba.fastjson.JSONObject;

public class TerrainUnitDataCodec implements IDataJSONCodec {

	protected int id;
	protected int enterpriseId;
	protected int type;

	@Override
	public JSONObject encode() {
		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("enterpriseId", enterpriseId);
		json.put("type", type);
		return json;
	}

	@Override
	public void decode(JSONObject source) {
		this.enterpriseId = source.getInteger("enterpriseId");
		this.type = source.getInteger("type");
	}

}
