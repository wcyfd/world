package org.aimfd.world.system.room.manager.module;

import org.aimfd.world.AData;
import org.aimfd.world.system.room.data.IRoomData;

public class UnRegistModule {
	private IRoomData roomData;

	public UnRegistModule() {
		roomData = AData.getASystem().getSystemAllData().getSystemPeaceData().getRoomData();
	}

	public void unRegist(String account) {
		roomData.getRoomMap().remove(account);
	}
}
