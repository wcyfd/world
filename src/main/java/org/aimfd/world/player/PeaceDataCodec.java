package org.aimfd.world.player;

import org.aimfd.base.IDataJSONCodec;
import org.aimfd.world.player.account.data.AccountData;
import org.aimfd.world.player.role.data.RoleData;

import com.alibaba.fastjson.JSONObject;

public class PeaceDataCodec implements IDataJSONCodec {
	protected AccountData accountData;
	protected RoleData roleData;

	@Override
	public JSONObject encode() {
		JSONObject json = new JSONObject();
		json.put("accountData", accountData.encode());
		json.put("roleData", roleData.encode());
		return json;
	}

	@Override
	public void decode(JSONObject source) {
		accountData.decode(source.getJSONObject("accountData"));
		roleData.decode(source.getJSONObject("roleData"));
	}
}
