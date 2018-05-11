package org.aimfd.world.player.account.data;

import org.aimfd.world.IDataCodec;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class AccountDataCodec implements IDataCodec<String, String> {
	protected String account;
	protected String name;

	public String encode() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("account", account);
		jsonObject.put("name", name);
		return jsonObject.toJSONString();
	}

	public void decode(String input) {
		JSONObject decoder = JSON.parseObject(input);
		account = decoder.getString("account");
		name = decoder.getString("name");

	}
}
