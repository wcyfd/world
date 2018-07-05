package org.aimfd.world.planet.aoi.data.unit;

import java.util.Set;

import org.aimfd.base.IDataJSONCodec;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * aoi区域
 * 
 * @author AIM
 *
 */
public class AOIGridUnitDataCodec implements IDataJSONCodec {

	protected Set<Integer> roleIdSet;
	protected int id;
	protected int x;
	protected int y;
	protected int width;
	protected int height;

	@Override
	public JSONObject encode() {
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
		array.addAll(roleIdSet);
		json.put("roleIdSet", array);
		json.put("id", id);
		json.put("x", x);
		json.put("y", y);
		json.put("width", width);
		json.put("height", height);

		return json;
	}

	@Override
	public void decode(JSONObject source) {

	}

}
