package org.aimfd.world.system.match.data;

import java.util.Queue;

import org.aimfd.base.IDataJSONCodec;

import com.alibaba.fastjson.JSONObject;

public class MatchDataCodec implements IDataJSONCodec {
	protected Queue<String> accountQueue;

	@Override
	public JSONObject encode() {
		JSONObject json = new JSONObject();
		json.put("accountQueue", accountQueue);
		return json;
	}

	@Override
	public void decode(JSONObject source) {
		accountQueue.clear();
		accountQueue.addAll(source.getJSONArray("accountQueue").toJavaList(String.class));
	}

}
