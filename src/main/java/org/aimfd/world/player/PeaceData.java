package org.aimfd.world.player;

import org.aimfd.base.IData;
import org.aimfd.world.player.account.data.AccountData;
import org.aimfd.world.player.account.data.IAccountData;
import org.aimfd.world.player.role.data.IRoleData;
import org.aimfd.world.player.role.data.RoleData;

public class PeaceData extends PeaceDataCodec implements IData {

	public PeaceData() {
		accountData = new AccountData();
		roleData = new RoleData();
	}

	@Override
	public void resetData() {
		accountData.resetData();
	}

	public IAccountData getAccountData() {
		return accountData;
	}

	public IRoleData getRoleData() {
		return roleData;
	}

}
