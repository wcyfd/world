package org.aimfd.world.planet.terrain.manager;

import org.aimfd.world.planet.PlanetManager;
import org.aimfd.world.planet.PlanetManagerRegistry;
import org.aimfd.world.planet.terrain.ITerrainPublic;
import org.aimfd.world.planet.terrain.manager.module.TerrainInitModule;
import org.springframework.stereotype.Service;

@Service
@PlanetManagerRegistry({ ITerrainPublic.class })
public class PlanetTerrainManager extends PlanetManager implements ITerrainPublic {

	private TerrainInitModule terrainInitModule;

	@Override
	public void init() {
		terrainInitModule = new TerrainInitModule(planet);
	}

	@Override
	public void terrainInit(int height, int width) {
		terrainInitModule.terrainInit(width, height);
	}

}
