package org.aimfd.world.planet.aoi.data.unit;

import java.util.Map;

import org.aimfd.base.IDataJSONCodec;
import org.aimfd.world.planet.role.data.IRoleData;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class AOIUnitDataCodec implements IDataJSONCodec {

	protected Map<String, IRoleData> roleDataMap;
	protected int id;

	@Override
	public JSONObject encode() {
		JSONObject json = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		for (IRoleData roleDataCodec : roleDataMap.values()) {
			IDataJSONCodec jsonCodec = (IDataJSONCodec) roleDataCodec;
			jsonArray.add(jsonCodec.encode());
		}
		json.put("roleDataMap", jsonArray);
		json.put("id", id);
		return json;
	}

	@Override
	public void decode(JSONObject source) {
		// 不能相信客户端的数据
	}

}
