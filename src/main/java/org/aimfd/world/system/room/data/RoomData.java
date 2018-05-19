package org.aimfd.world.system.room.data;

import java.util.HashMap;
import java.util.Map;

public class RoomData extends RoomDataCodec implements IRoomData {
	public RoomData() {
		roomMap = new HashMap<>();
	}

	@Override
	public Map<String, Integer> getRoomMap() {
		return roomMap;
	}
}
