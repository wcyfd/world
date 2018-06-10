package org.aimfd.world.planet.enterprise.data;

import java.util.HashMap;
import java.util.Map;

import org.aimfd.base.IData;
import org.aimfd.world.planet.enterprise.data.unit.EnterpriseUnitData;
import org.aimfd.world.planet.enterprise.data.unit.IEnterpriseUnitData;

/**
 * 所有企业的数据
 * 
 * @author AIM
 *
 */
public class EnterpriseData extends EnterpriseDataCodec implements IData, IEnterpriseData {

	public EnterpriseData() {
		this.accountMap = new HashMap<>();
		this.unitDataMap = new HashMap<>();
		for (int id = 0; id < 5; id++) {
			EnterpriseUnitData unitData = new EnterpriseUnitData(id);
			this.unitDataMap.put(id, unitData);
		}
	}

	@Override
	public IEnterpriseUnitData getEnterpriseUnitData(int id) {
		return this.unitDataMap.get(id);
	}

	@Override
	public IEnterpriseUnitData getEnterpriseUnitData(String account) {
		int id = this.accountMap.get(account);
		return this.unitDataMap.get(id);
	}

	@Override
	public Map<Integer, IEnterpriseUnitData> getUnitData() {
		return unitDataMap;
	}

	@Override
	public Map<String, Integer> getAccounts() {
		return accountMap;
	}

	@Override
	public void resetData() {
		this.accountMap.clear();
		for (Map.Entry<Integer, IEnterpriseUnitData> entrySet : this.unitDataMap.entrySet()) {
			EnterpriseUnitData unitData = (EnterpriseUnitData) entrySet.getValue();
			unitData.resetData();
		}
	}

}
