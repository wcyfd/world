package org.aimfd.world.planet.terrain.data;

import java.util.Map;

import org.aimfd.base.IDataCodec;
import org.aimfd.world.planet.terrain.data.unit.TerrainUnitData;

public class TerrainDataCodec implements IDataCodec<String, String> {

	protected int height;
	protected int width;
	protected Map<Integer, TerrainUnitData> unitDataMap;

	@Override
	public String encode() {
		return null;
	}

	@Override
	public void decode(String input) {

	}

}
