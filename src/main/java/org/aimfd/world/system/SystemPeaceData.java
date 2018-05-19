package org.aimfd.world.system;

import org.aimfd.world.system.room.data.IRoomData;
import org.aimfd.world.system.room.data.RoomData;

public class SystemPeaceData extends SystemPeaceDataCodec {
	public SystemPeaceData() {
		roomData = new RoomData();
	}

	public IRoomData getRoomData() {
		return roomData;
	}

}
