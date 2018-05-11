package org.aimfd.world.planet.enterprise.data;

import org.aimfd.world.planet.enterprise.data.unit.IEnterpriseUnitData;

public interface IEnterpriseData {
	/**
	 * 设置企业数量
	 * 
	 * @param enterpriseCount
	 */
	void setEnterpriseCount(int enterpriseCount);

	/**
	 * 获得企业数量
	 * 
	 * @return
	 */
	int getEnterpriseCount();

	/**
	 * 
	 * @param id
	 * @return
	 */
	IEnterpriseUnitData getEnterpriseUnitData(int id);

	IEnterpriseUnitData getEnterpriseUnitData(String account);
}
