package org.aimfd.world.planet.terrain;

import org.aimfd.world.planet.Planet;
import org.aimfd.world.planet.terrain.data.ITerrainData;
import org.aimfd.world.planet.terrain.data.unit.ITerrainUnitData;
import org.aimfd.world.planet.terrain.manager.module.TerrainInitModule;
import org.junit.Before;
import org.junit.Test;

public class TestTerrain {
	private Planet planet;
	private TerrainInitModule module;

	@Before
	public void before() {
		planet = new Planet(1);

		module = new TerrainInitModule(planet);

	}

	@Test
	public void exec() {
		module.terrainInit(2, 3);
		ITerrainData terrainData = planet.getPlanetAllData().getPlanetData().getTerrainData();
		for (int i = 0; i < terrainData.getTerrainUnitCount(); i++) {
			ITerrainUnitData unitData = terrainData.getTerrainUnitData(i);
			System.out.println(unitData.getId() + "" + unitData.getTerrainUnits());
		}
	}
}
