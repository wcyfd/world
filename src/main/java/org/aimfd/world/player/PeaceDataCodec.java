package org.aimfd.world.player;

import org.aimfd.world.IDataCodec;
import org.aimfd.world.player.account.data.AccountData;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class PeaceDataCodec implements IDataCodec<String, String> {
	protected AccountData accountData;

	@Override
	public String encode() {
		JSONObject encoder = new JSONObject();
		encoder.put("accountData", accountData.encode());
		return encoder.toJSONString();
	}

	@Override
	public void decode(String input) {
		JSONObject decoder = JSON.parseObject(input);
		accountData.decode(decoder.getJSONObject("accountData").toJSONString());
	}
}
