package org.aimfd.world.planet.environment.data;

import org.aimfd.base.IDataJSONCodec;

import com.alibaba.fastjson.JSONObject;

public class EnvironmentDataCodec implements IDataJSONCodec {

	protected int oxygen;
	protected int temperature;

	@Override
	public JSONObject encode() {
		JSONObject json = new JSONObject();
		json.put("oxygen", oxygen);
		json.put("temperature", temperature);
		return json;
	}

	@Override
	public void decode(JSONObject source) {
		this.oxygen = source.getIntValue("oxygen");
		this.temperature = source.getIntValue("temperature");
	}

}
