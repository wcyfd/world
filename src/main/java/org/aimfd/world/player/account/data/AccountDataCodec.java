package org.aimfd.world.player.account.data;

import org.aimfd.base.IDataJSONCodec;

import com.alibaba.fastjson.JSONObject;

public class AccountDataCodec implements IDataJSONCodec {
	protected String account;
	protected String name;

	@Override
	public JSONObject encode() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("account", account);
		jsonObject.put("name", name);
		return jsonObject;
	}

	@Override
	public void decode(JSONObject source) {
		account = source.getString("account");
		name = source.getString("name");
	}
}
