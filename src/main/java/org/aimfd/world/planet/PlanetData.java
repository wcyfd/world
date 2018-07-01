package org.aimfd.world.planet;

import org.aimfd.base.IData;
import org.aimfd.world.planet.enterprise.data.EnterpriseData;
import org.aimfd.world.planet.enterprise.data.IEnterpriseData;
import org.aimfd.world.planet.environment.data.EnvironmentData;
import org.aimfd.world.planet.environment.data.IEnvironmentData;
import org.aimfd.world.planet.roles.data.IRolesData;
import org.aimfd.world.planet.roles.data.RolesData;
import org.aimfd.world.planet.terrain.data.ITerrainData;
import org.aimfd.world.planet.terrain.data.TerrainData;

/**
 * 星球
 * 
 * @author AIM
 *
 */
public class PlanetData extends PlanetDataCodec implements IData, IPlanetData {

	public PlanetData() {
		this.enterpriseData = new EnterpriseData();
		this.environmentData = new EnvironmentData();
		this.terrainData = new TerrainData();
		this.roleData = new RolesData();
	}

	@Override
	public IEnvironmentData getEnviromentData() {
		return environmentData;
	}

	@Override
	public IEnterpriseData getEnterpriseData() {
		return enterpriseData;
	}

	@Override
	public ITerrainData getTerrainData() {
		return terrainData;
	}

	@Override
	public IRolesData getRolesData() {
		return roleData;
	}

	@Override
	public void resetData() {
		this.environmentData.resetData();
		this.enterpriseData.resetData();
		this.terrainData.resetData();
		((IData) this.roleData).resetData();
	}

}
