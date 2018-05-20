package org.aimfd.world.system;

import org.aimfd.world.system.match.data.IMatchData;
import org.aimfd.world.system.match.data.MatchData;
import org.aimfd.world.system.room.data.IRoomData;
import org.aimfd.world.system.room.data.RoomData;

public class SystemPeaceData extends SystemPeaceDataCodec {
	public SystemPeaceData() {
		roomData = new RoomData();
		matchData = new MatchData();
	}

	public IRoomData getRoomData() {
		return roomData;
	}

	public IMatchData getMatchData() {
		return matchData;
	}
}
