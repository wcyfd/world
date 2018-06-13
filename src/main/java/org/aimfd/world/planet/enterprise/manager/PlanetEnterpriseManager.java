package org.aimfd.world.planet.enterprise.manager;

import java.util.List;

import org.aimfd.world.planet.PlanetManager;
import org.aimfd.world.planet.enterprise.IEnterpriseInternal;
import org.aimfd.world.planet.enterprise.IEnterprisePublic;
import org.aimfd.world.planet.enterprise.manager.module.EnterpriseInitModule;
import org.aimfd.world.planet.enterprise.manager.module.EnterpriseStartModule;

public class PlanetEnterpriseManager extends PlanetManager implements IEnterprisePublic, IEnterpriseInternal {
	private EnterpriseInitModule enterpriseInitModule;
	private EnterpriseStartModule startModule;

	@Override
	public void init() {
		enterpriseInitModule = new EnterpriseInitModule(planet);
		startModule = new EnterpriseStartModule(planet);
	}

	@Override
	public void applyEnterprises(List<String> accounts) {
		enterpriseInitModule.applyEnterprises(accounts);
	}

	@Override
	protected void onStart() {
		startModule.onStart();
	}

	@Override
	public List<Integer> getClientIds() {
		return enterpriseInitModule.getClientIds();
	}

	@Override
	public int getEnterpriseId(String account) {
		return enterpriseInitModule.getEnterpriseId(account);
	}
}
