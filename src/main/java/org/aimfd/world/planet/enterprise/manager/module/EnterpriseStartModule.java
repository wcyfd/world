package org.aimfd.world.planet.enterprise.manager.module;

import java.util.Map;

import org.aimfd.world.planet.Planet;
import org.aimfd.world.planet.enterprise.data.IEnterpriseData;
import org.aimfd.world.planet.enterprise.data.unit.IEnterpriseUnitData;

public class EnterpriseStartModule {
	private IEnterpriseData enterpriseData;

	public EnterpriseStartModule(Planet planet) {
		this.enterpriseData = planet.getPlanetAllData().getPlanetData().getEnterpriseData();
	}

	public void onStart() {
		Map<Integer, IEnterpriseUnitData> unitMap = enterpriseData.getUnitData();
		for (IEnterpriseUnitData unitData : unitMap.values()) {
			unitData.setBonus(10);
			unitData.setIron(0);
			unitData.setTi(0);
			unitData.setTr(0);
		}
	}
}
