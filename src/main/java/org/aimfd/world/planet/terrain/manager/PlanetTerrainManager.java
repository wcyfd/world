package org.aimfd.world.planet.terrain.manager;

import org.aimfd.world.planet.PlanetManager;
import org.aimfd.world.planet.terrain.ITerrainPublic;
import org.aimfd.world.planet.terrain.manager.module.TerrainPutModule;
import org.aimfd.world.planet.terrain.manager.module.TerrainStartModule;

public class PlanetTerrainManager extends PlanetManager implements ITerrainPublic {

	private TerrainStartModule terrainStartModule;
	private TerrainPutModule terrainPutModule;

	@Override
	public void init() {
		terrainStartModule = new TerrainStartModule(planet);
		terrainPutModule = new TerrainPutModule(planet);
	}

	@Override
	public void onStart() {
		terrainStartModule.onStart();
	}

	@Override
	public void setSize(int height, int width) {
		terrainStartModule.setSize(width, height);
	}

	@Override
	public void putTerrain(int id, int enterpriseId, int type) {
		if (enterpriseId == -1)
			return;
		terrainPutModule.putTerrain(type, id, enterpriseId);
	}

}
