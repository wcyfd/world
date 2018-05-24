package org.aimfd.world.player;

import org.aimfd.base.IData;
import org.aimfd.world.player.account.data.AccountData;
import org.aimfd.world.player.account.data.IAccountData;

public class PeaceData extends PeaceDataCodec implements IData {

	public PeaceData() {
		accountData = new AccountData();
	}

	public void resetData() {
		accountData.resetData();
	}

	public IAccountData getAccountData() {
		return accountData;
	}

}
