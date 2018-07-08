package org.aimfd.world.player.role.data.unit;

import org.aimfd.base.IDataJSONCodec;

import com.alibaba.fastjson.JSONObject;

public class RoleCoordUnitDataCodec implements IDataJSONCodec {

	protected int roleId;
	protected int x;
	protected int y;

	@Override
	public JSONObject encode() {
		JSONObject json = new JSONObject();

		json.put("roleId", roleId);
		json.put("x", x);
		json.put("y", y);

		return json;
	}

	@Override
	public void decode(JSONObject source) {
		roleId = source.getIntValue("roleId");
		x = source.getIntValue("x");
		y = source.getIntValue("y");
	}

}
