package org.aimfd.world.system.room.data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RoomData extends RoomDataCodec implements IRoomData {
	public RoomData() {
		roomMap = new ConcurrentHashMap<>();
	}

	@Override
	public Map<String, Integer> getRoomMap() {
		return roomMap;
	}
}
