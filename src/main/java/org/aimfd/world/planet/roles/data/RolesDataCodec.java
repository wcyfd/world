package org.aimfd.world.planet.roles.data;

import java.util.Map;

import org.aimfd.base.IDataJSONCodec;
import org.aimfd.world.player.role.data.IRoleData;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class RolesDataCodec implements IDataJSONCodec {

	protected Map<Integer, IRoleData> roleDataMap;

	@Override
	public JSONObject encode() {
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
		json.put("roleDataMap", array);
		for (IRoleData unitData : roleDataMap.values()) {
			IDataJSONCodec unitDataCodec = (IDataJSONCodec) unitData;
			array.add(unitDataCodec.encode());
		}
		return json;
	}

	@Override
	public void decode(JSONObject source) {

	}

}
