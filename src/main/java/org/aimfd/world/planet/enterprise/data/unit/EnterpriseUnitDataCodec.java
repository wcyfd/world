package org.aimfd.world.planet.enterprise.data.unit;

import org.aimfd.base.IDataJSONCodec;

import com.alibaba.fastjson.JSONObject;

public class EnterpriseUnitDataCodec implements IDataJSONCodec {

	protected int id;
	protected String account;
	protected int bonus;
	protected int tr;
	protected int iron;
	protected int ti;

	@Override
	public JSONObject encode() {
		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("account", account);
		json.put("bonus", bonus);
		json.put("tr", tr);
		json.put("iron", iron);
		json.put("ti", ti);
		return json;
	}

	@Override
	public void decode(JSONObject source) {
		this.id = source.getInteger("id");
		this.account = source.getString("account");
		this.bonus = source.getInteger("bonus");
		this.tr = source.getInteger("tr");
		this.iron = source.getInteger("iron");
		this.ti = source.getInteger("ti");
	}

}
