package org.aimfd.world.player.role.data;

import org.aimfd.base.IDataJSONCodec;

import com.alibaba.fastjson.JSONObject;

public class RoleDataCodec implements IDataJSONCodec{
	protected String name;
	protected String account;
	protected int x;
	protected int y;

	@Override
	public JSONObject encode() {
		JSONObject json = new JSONObject();
		json.put("name", name);
		json.put("account", account);
		json.put("x", x);
		json.put("y", y);
		return json;
	}

	@Override
	public void decode(JSONObject source) {
		this.name = source.getString("name");
		this.account = source.getString("account");
		this.x = source.getIntValue("x");
		this.y = source.getIntValue("y");
	}
}
