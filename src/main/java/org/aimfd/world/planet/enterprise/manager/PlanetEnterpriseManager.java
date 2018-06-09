package org.aimfd.world.planet.enterprise.manager;

import java.util.List;

import org.aimfd.world.planet.PlanetManager;
import org.aimfd.world.planet.enterprise.IEnterpriseInternal;
import org.aimfd.world.planet.enterprise.IEnterprisePublic;
import org.aimfd.world.planet.enterprise.manager.module.EnterpriseInitModule;

public class PlanetEnterpriseManager extends PlanetManager implements IEnterprisePublic, IEnterpriseInternal {
	private EnterpriseInitModule enterpriseInitModule;

	@Override
	public void init() {
		enterpriseInitModule = new EnterpriseInitModule(planet);
	}

	@Override
	public void setEnterprises(List<String> accounts) {
		enterpriseInitModule.enterpriseInit(accounts);
	}

	@Override
	protected void onStart() {
	}
}
