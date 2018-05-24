package org.aimfd.world.player.account.db;

import org.aimfd.base.IDBAPI;
import org.aimfd.world.player.Player;
import org.aimfd.world.player.account.data.IAccountData;

public class AccountDB implements IDBAPI {

	private IAccountData accountData;
	private Player player;

	public AccountDB(Player player) {
		this.accountData = player.getPlayerAllData().getPeaceData().getAccountData();
		this.player = player;
	}

	@Override
	public void dbCreate() {
		
	}

	@Override
	public void dbSave() {
		
	}

	@Override
	public void dbLoad() {
//		DBManager.loadTable(AccountTable.class, accountData, "findByAccount", new Class<?>[] { String.class }, new Object[] { player.getAccount() });
	}

}
