package org.aimfd.world.planet.enterprise.data;

import java.util.Map;
import java.util.Map.Entry;

import org.aimfd.base.IData;
import org.aimfd.base.IDataJSONCodec;
import org.aimfd.world.planet.enterprise.data.unit.IEnterpriseUnitData;

import com.alibaba.fastjson.JSONObject;

public class EnterpriseDataCodec implements IDataJSONCodec {

	/** 企业映射表 */
	protected Map<Integer, IEnterpriseUnitData> unitDataMap;
	/** 企业帐号 */
	protected Map<String, Integer> accountMap;

	@Override
	public JSONObject encode() {
		JSONObject json = new JSONObject();
		json.put("accountMap", accountMap);
		JSONObject unitDataMapJson = new JSONObject();
		json.put("unitDataMap", unitDataMapJson);
		for (int i = 0; i < accountMap.size(); i++) {
			IEnterpriseUnitData unitData = this.unitDataMap.get(i);
			String id = String.valueOf(i);
			unitDataMapJson.put(id, ((IDataJSONCodec) unitData).encode());
		}
		return json;
	}

	@Override
	public void decode(JSONObject source) {
		((IData) this).resetData();

		JSONObject accountMapJson = source.getJSONObject("accountMap");
		for (Entry<String, Object> entrySet : accountMapJson.entrySet()) {
			String account = entrySet.getKey();
			int id = accountMapJson.getInteger(account);
			accountMap.put(account, id);
		}

		JSONObject unitDataMapJson = source.getJSONObject("unitDataMap");
		for (int i = 0; i < accountMap.size(); i++) {
			JSONObject unitDataJson = unitDataMapJson.getJSONObject(String.valueOf(i));
			IEnterpriseUnitData unitData = unitDataMap.get(i);
			((IDataJSONCodec) unitData).decode(unitDataJson);
		}

	}

}
