package org.aimfd.world.planet;

public class PlanetAllData {
	private PlanetData planetData;

	public PlanetAllData() {
		this.planetData = new PlanetData();
	}

	public PlanetData getPlanetData() {
		return planetData;
	}

	public void resetData() {
		planetData.resetData();
	}
}
