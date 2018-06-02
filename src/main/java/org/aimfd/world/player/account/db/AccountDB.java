package org.aimfd.world.player.account.db;

import org.aimfd.base.IDBAPI;
import org.aimfd.base.SpringContext;
import org.aimfd.world.dao.AccountTableDao;
import org.aimfd.world.entity.AccountTable;
import org.aimfd.world.player.Player;
import org.aimfd.world.player.account.data.IAccountData;

public class AccountDB implements IDBAPI {

	private IAccountData accountData;
	private Player player;
	private AccountTableDao accountTableDao;

	public AccountDB(Player player) {
		this.accountData = player.getPlayerAllData().getPeaceData().getAccountData();
		this.player = player;
		this.accountTableDao = SpringContext.getContext().getBean(AccountTableDao.class);
	}

	@Override
	public void dbCreate() {
		AccountTable table = new AccountTable();
		table.account = accountData.getAccount();
		table.name = accountData.getName();

		Integer id = accountTableDao.insert(table);
		table.id = id;

		accountData.setAccountId(id);
	}

	@Override
	public void dbSave() {

	}

	@Override
	public void dbLoad() {
		// DBManager.loadTable(AccountTable.class, accountData, "findByAccount", new
		// Class<?>[] { String.class }, new Object[] { player.getAccount() });
	}

	public boolean checkAccount(String account) {
		Integer id = accountTableDao.findIdByAccount(account);
		return id != null;
	}
}
