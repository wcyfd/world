package org.aimfd.world.planet.terrain4.data;

import java.util.HashMap;
import java.util.Map;

import org.aimfd.base.IData;
import org.aimfd.world.planet.terrain4.data.unit.ITerrain4UnitData;
import org.aimfd.world.planet.terrain4.data.unit.Terrain4UnitData;

public class Terrain4Data extends Terrain4DataCodec implements IData, ITerrain4Data {

	public Terrain4Data() {
		this.unitMap = new HashMap<>();
		this.width = 0;
		this.height = 0;
		this.count = 0;

		for (int i = 0; i < 100; i++) {
			ITerrain4UnitData unitData = new Terrain4UnitData();
			unitData.setId(i);
			unitMap.put(i, unitData);
		}
	}

	@Override
	public void setWidth(int width) {
		this.width = width;
	}

	@Override
	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public ITerrain4UnitData getITerrain4Data(int id) {
		return unitMap.get(id);
	}

	@Override
	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public int getCount() {
		return count;
	}

	@Override
	public void resetData() {
		this.width = 0;
		this.height = 0;
		this.count = 0;
		for (Map.Entry<Integer, ITerrain4UnitData> entrySet : unitMap.entrySet()) {
			ITerrain4UnitData unitData = entrySet.getValue();
			((IData) unitData).resetData();
		}

	}

}
