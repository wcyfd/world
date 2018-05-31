package org.aimfd.world.system.match.manager.module;

import java.util.Set;

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
		Set<String> accountSet = matchData.getAccountSet();
		if (accountSet.contains(account)) {
			accountSet.remove(account);
		}
	}
}
