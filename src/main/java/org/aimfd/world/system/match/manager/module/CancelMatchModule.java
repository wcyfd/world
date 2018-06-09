package org.aimfd.world.system.match.manager.module;

import org.aimfd.world.AData;
import org.aimfd.world.system.match.data.IMatchData;

public class CancelMatchModule {
	private IMatchData matchData;

	public CancelMatchModule() {
		matchData = AData.getASystem().getSystemAllData().getSystemPeaceData().getMatchData();
	}

	public void cancelMatch(String account) {
		cancel(account);
	}

	/**
	 * 
	 * @param account
	 */
	private void cancel(String account) {
		if (matchData.contains(account)) {
			matchData.removeAccount(account);
		}
	}
}
