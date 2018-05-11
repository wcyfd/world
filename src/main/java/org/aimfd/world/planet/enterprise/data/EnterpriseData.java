package org.aimfd.world.planet.enterprise.data;

import java.util.HashMap;
import java.util.Map;

import org.aimfd.world.IData;
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
		this.enterpriseAccountMap = new HashMap<>();
		this.enterpriseUnitDataMap = new HashMap<>();
		for (int id = 0; id < 5; id++) {
			EnterpriseUnitData unitData = new EnterpriseUnitData(id);
			this.enterpriseUnitDataMap.put(id, unitData);
		}
	}

	@Override
	public void setEnterpriseCount(int enterpriseCount) {
		this.enterpriseCount = enterpriseCount;
	}

	@Override
	public IEnterpriseUnitData getEnterpriseUnitData(int id) {
		return this.enterpriseUnitDataMap.get(id);
	}

	@Override
	public int getEnterpriseCount() {
		return enterpriseCount;
	}

	@Override
	public IEnterpriseUnitData getEnterpriseUnitData(String account) {
		int id = this.enterpriseAccountMap.get(account);
		return this.enterpriseUnitDataMap.get(id);
	}

	@Override
	public void resetData() {
		this.enterpriseAccountMap.clear();
		this.enterpriseCount = -1;
		for (Map.Entry<Integer, EnterpriseUnitData> entrySet : this.enterpriseUnitDataMap.entrySet()) {
			EnterpriseUnitData unitData = entrySet.getValue();
			unitData.resetData();
		}
	}

}
