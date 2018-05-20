package org.aimfd.world.system;

import org.aimfd.world.IDataCodec;
import org.aimfd.world.system.match.data.MatchData;
import org.aimfd.world.system.room.data.RoomData;

public class SystemPeaceDataCodec implements IDataCodec<String, String> {

	protected RoomData roomData;
	protected MatchData matchData;

	@Override
	public String encode() {
		return null;
	}

	@Override
	public void decode(String input) {
		// TODO Auto-generated method stub

	}

}
