package org.aimfd.world.planet.terrain.data;

import java.util.HashMap;

import org.aimfd.world.IData;
import org.aimfd.world.planet.terrain.data.unit.ITerrainUnitData;
import org.aimfd.world.planet.terrain.data.unit.TerrainUnitData;

public class TerrainData extends TerrainDataCodec implements ITerrainData, IData {

	private int terrainUnitCount;

	public TerrainData() {
		this.height = 0;
		this.width = 0;
		this.terrainUnitCount = 0;
		this.unitDataMap = new HashMap<>();
		for (int i = 0; i < 60; i++) {
			TerrainUnitData terrainUnitData = new TerrainUnitData();
			terrainUnitData.setId(i);
			unitDataMap.put(i, terrainUnitData);
		}
	}

	@Override
	public ITerrainUnitData getTerrainUnitData(int id) {
		return unitDataMap.get(id);
	}

	@Override
	public void setTerrainHeight(int height) {
		this.height = height;
	}

	@Override
	public int getTerrainHeight() {
		return height;
	}

	@Override
	public void setTerrainWidth(int width) {
		this.width = width;
	}

	@Override
	public int getTerrainWidth() {
		return width;
	}

	@Override
	public int getTerrainUnitCount() {
		return terrainUnitCount;
	}

	@Override
	public void setTerrainUnitCount(int count) {
		this.terrainUnitCount = count;
	}

	@Override
	public void resetData() {
		this.height = -1;
		this.width = -1;
		this.terrainUnitCount = -1;
		for (TerrainUnitData unitData : unitDataMap.values()) {
			unitData.resetData();
		}
	}

}
