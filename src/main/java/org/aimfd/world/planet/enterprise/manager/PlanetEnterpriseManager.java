package org.aimfd.world.planet.enterprise.manager;

import org.aimfd.world.planet.PlanetManager;
import org.aimfd.world.planet.PlanetManagerRegistry;
import org.aimfd.world.planet.enterprise.IEnterpriseInternal;
import org.aimfd.world.planet.enterprise.IEnterprisePublic;
import org.aimfd.world.planet.enterprise.data.IEnterpriseData;
import org.springframework.stereotype.Service;

@Service
@PlanetManagerRegistry({ IEnterprisePublic.class, IEnterpriseInternal.class })
public class PlanetEnterpriseManager extends PlanetManager implements IEnterprisePublic, IEnterpriseInternal {
	private IEnterpriseData enterpriseData;

	@Override
	public void init() {
		enterpriseData = planet.getPlanetAllData().getPlanetData().getEnterpriseData();
	}

	@Override
	public void onEnter() {
	}

	@Override
	public void initEnterpriseCount(int count) {
		enterpriseData.setEnterpriseCount(count);
	}
}
