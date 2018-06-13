package org.aimfd.world.planet.terrain;

import org.aimfd.world.planet.Planet;
import org.aimfd.world.planet.terrain.data.ITerrainData;
import org.aimfd.world.planet.terrain.data.TerrainDataCodec;
import org.aimfd.world.planet.terrain.data.unit.ITerrainUnitData;
import org.aimfd.world.planet.terrain.manager.module.TerrainStartModule;
import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

public class TestTerrain {
	private Planet planet;
	private TerrainStartModule module;

	@Before
	public void before() {
		planet = new Planet(1);
		planet.setLogicName("demo_planet");

		module = new TerrainStartModule(planet);

	}

	// int LEFT_TOP = 0;
	// int RIGHT_TOP = 1;
	// int RIGHT = 2;
	// int RIGHT_BUTTOM = 3;
	// int LEFT_BUTTOM = 4;
	// int LEFT = 5;

//	@Test
	public void exec() {
		module.setSize(3, 3);
		module.onStart();
		ITerrainData terrainData = planet.getPlanetAllData().getPlanetData().getTerrainData();
		for (int i = 0; i < terrainData.getTerrainUnitCount(); i++) {
			ITerrainUnitData unitData = terrainData.getTerrainUnitData(i);
			System.out.println(unitData.getId() + "" + unitData.getTerrainUnits());
		}
	}

	@Test
	public void testTerrainDataCodec() {
		module.setSize(3, 3);
		module.onStart();
		JSONObject json = ((TerrainDataCodec) planet.getPlanetAllData().getPlanetData().getTerrainData()).encode();
		System.out.println(json);
	}
}
