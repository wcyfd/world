package org.aimfd.world.planet.enterprise.data;

import java.util.Map;

import org.aimfd.world.planet.enterprise.data.unit.IEnterpriseUnitData;

public interface IEnterpriseData {

	/**
	 * 
	 * @param id
	 * @return
	 */
	IEnterpriseUnitData getEnterpriseUnitData(int id);

	IEnterpriseUnitData getEnterpriseUnitData(String account);

	Map<Integer, IEnterpriseUnitData> getUnitData();

	Map<String, Integer> getAccounts();

}
