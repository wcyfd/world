package org.aimfd.world.system.match.data;

import java.util.Set;

import org.aimfd.base.IDataJSONCodec;

import com.alibaba.fastjson.JSONObject;

public class MatchDataCodec implements IDataJSONCodec {
	protected Set<String> accountSet;

	@Override
	public JSONObject encode() {
		JSONObject json = new JSONObject();
		json.put("accountSet", accountSet);
		return json;
	}

	@Override
	public void decode(JSONObject source) {
		accountSet.addAll(source.getJSONArray("accountSet").toJavaList(String.class));
	}

}
