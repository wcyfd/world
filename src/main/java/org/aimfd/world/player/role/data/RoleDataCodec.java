package org.aimfd.world.player.role.data;

import org.aimfd.base.IDataJSONCodec;
import org.aimfd.world.player.role.data.unit.IRoleCoordUnitData;

import com.alibaba.fastjson.JSONObject;

public class RoleDataCodec implements IDataJSONCodec {
	protected String name;
	protected String account;
	protected int roleId;
	protected IRoleCoordUnitData coordData;

	@Override
	public JSONObject encode() {
		JSONObject json = new JSONObject();
		json.put("name", name);
		json.put("account", account);
		json.put("roleId", roleId);
		json.put("coord", ((IDataJSONCodec) coordData).encode());
		return json;
	}

	@Override
	public void decode(JSONObject source) {
		this.name = source.getString("name");
		this.account = source.getString("account");
		this.roleId = source.getIntValue("roleId");
		((IDataJSONCodec) coordData).decode(source.getJSONObject("coord"));
	}
}
