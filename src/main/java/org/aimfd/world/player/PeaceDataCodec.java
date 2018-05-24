package org.aimfd.world.player;

import org.aimfd.base.IDataJSONCodec;
import org.aimfd.world.player.account.data.AccountData;

import com.alibaba.fastjson.JSONObject;

public class PeaceDataCodec implements IDataJSONCodec {
	protected AccountData accountData;

	@Override
	public JSONObject encode() {
		JSONObject encoder = new JSONObject();
		encoder.put("accountData", accountData.encode());
		return encoder;
	}

	@Override
	public void decode(JSONObject source) {
		accountData.decode(source.getJSONObject("accountData"));
	}
}
