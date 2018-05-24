package org.aimfd.world.planet.environment.data;

import org.aimfd.base.IData;

public class EnvironmentData extends EnvironmentDataCodec implements IEnvironmentData, IData {

	@Override
	public void setOxygen(int oxygen) {
		this.oxygen = oxygen;
	}

	@Override
	public int getOxygen() {
		return oxygen;
	}

	@Override
	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	@Override
	public int getTemperature() {
		return temperature;
	}

	@Override
	public void resetData() {
		temperature = -1;
		oxygen = -1;
	}

}
