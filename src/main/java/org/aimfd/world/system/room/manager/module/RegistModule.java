package org.aimfd.world.system.room.manager.module;

import org.aimfd.world.AData;
import org.aimfd.world.system.room.data.IRoomData;

public class RegistModule {

	private IRoomData roomData;

	public RegistModule() {
		roomData = AData.getASystem().getSystemAllData().getSystemPeaceData().getRoomData();
	}

	public void regist(String account, int planetId) {
		roomData.getRoomMap().put(account, planetId);
	}

	public boolean atRoom(String account) {
		return roomData.getRoomMap().containsKey(account);
	}
}
