package org.aimfd.world.planet;

import org.aimfd.world.IDataCodec;
import org.aimfd.world.planet.enterprise.data.EnterpriseData;
import org.aimfd.world.planet.environment.data.EnvironmentData;
import org.aimfd.world.planet.terrain.data.TerrainData;

public class PlanetDataCodec implements IDataCodec<String, String> {

	protected EnvironmentData environmentData;
	protected EnterpriseData enterpriseData;
	protected TerrainData terrainData;

	@Override
	public String encode() {
		return null;
	}

	@Override
	public void decode(String input) {

	}
}
