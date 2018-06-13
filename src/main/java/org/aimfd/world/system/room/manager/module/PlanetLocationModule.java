package org.aimfd.world.system.room.manager.module;

import org.aimfd.world.AData;
import org.aimfd.world.system.room.data.IRoomData;

public class PlanetLocationModule {

	private IRoomData roomData;

	public PlanetLocationModule() {
		roomData = AData.getASystem().getSystemAllData().getSystemPeaceData().getRoomData();
	}

	/**
	 * 获取账号所在的星球id
	 * 
	 * @param account
	 * @return
	 */
	public int getPlanetId(String account) {
		Integer planetId = roomData.getRoomMap().get(account);
		if (planetId == null) {
			planetId = -1;
		}

		return planetId;
	}

}
