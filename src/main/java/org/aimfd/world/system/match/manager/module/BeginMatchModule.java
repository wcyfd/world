package org.aimfd.world.system.match.manager.module;

import java.util.Set;

import org.aimfd.world.AData;
import org.aimfd.world.system.match.data.IMatchData;
import org.aimfd.world.system.room.IRoomPublic;

public class BeginMatchModule {

	private IMatchData matchData;
	private IRoomPublic roomPublic;

	public BeginMatchModule() {
		matchData = AData.getSystemAllData().getSystemPeaceData().getMatchData();
		roomPublic = AData.getASystem().getSystemManager(IRoomPublic.class);
	}

	public void beginMatch(String account) {
		match(account);
	}

	/**
	 * 匹配
	 * 
	 * @param account
	 */
	private void match(String account) {
		Set<String> accountSet = matchData.getAccountSet();
		if (accountSet.contains(account)) {
			return;
		}

		// 如果已经在房间中则返回
		if (roomPublic.atRoom(account)) {
			return;
		}

		accountSet.add(account);
	}

}
