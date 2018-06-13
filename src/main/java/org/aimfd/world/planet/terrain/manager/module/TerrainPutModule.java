package org.aimfd.world.planet.terrain.manager.module;

import org.aimfd.world.planet.Planet;
import org.aimfd.world.planet.terrain.data.ITerrainData;
import org.aimfd.world.planet.terrain.data.unit.ITerrainUnitData;
import org.slf4j.Logger;

public class TerrainPutModule {
	private Logger logger;
	private ITerrainData terrainData;

	public TerrainPutModule(Planet planet) {
		this.logger = planet.logger();
		this.terrainData = planet.getPlanetAllData().getPlanetData().getTerrainData();
	}

	/**
	 * 放置瓦片
	 * 
	 * @param type
	 * @param id
	 * @param enterpriseId
	 */
	public void putTerrain(int type, int id, int enterpriseId) {
		ITerrainUnitData unitData = terrainData.getTerrainUnitData(id);
		unitData.setEnterpriseId(enterpriseId);
		unitData.setType(type);
	}

}
