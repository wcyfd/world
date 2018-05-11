package org.aimfd.world.player;

public class PlayerAllData {
	private PeaceData peaceData;

	public PlayerAllData() {
		this.peaceData = new PeaceData();
	}

	public PeaceData getPeaceData() {
		return peaceData;
	}

	public void resetData() {
		peaceData.resetData();
	}

}
