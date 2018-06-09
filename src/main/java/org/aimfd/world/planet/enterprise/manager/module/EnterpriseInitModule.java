package org.aimfd.world.planet.enterprise.manager.module;

import java.util.List;

import org.aimfd.world.planet.Planet;
import org.aimfd.world.planet.enterprise.data.IEnterpriseData;
import org.aimfd.world.planet.enterprise.data.unit.IEnterpriseUnitData;

public class EnterpriseInitModule {
	private Planet planet;
	private IEnterpriseData enterpriseData;

	public EnterpriseInitModule(Planet planet) {
		this.planet = planet;
		this.enterpriseData = planet.getPlanetAllData().getPlanetData().getEnterpriseData();
	}

	/**
	 * 设置参与的企业
	 * 
	 * @param accounts
	 */
	public void enterpriseInit(List<String> accounts) {
		enterpriseData.setEnterpriseCount(accounts.size());
		for (int i = 0; i < accounts.size(); i++) {
			IEnterpriseUnitData unitData = enterpriseData.getEnterpriseUnitData(i);
			unitData.setAccount(accounts.get(i));
		}
	}

}
