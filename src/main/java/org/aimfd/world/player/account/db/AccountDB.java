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
	private AccountTable accountTableBackup;

	public AccountDB(Player player) {
		this.accountData = player.getPlayerAllData().getPeaceData().getAccountData();
		this.player = player;
		this.accountTableDao = SpringContext.getContext().getBean(AccountTableDao.class);
		this.accountTableBackup = new AccountTable();
	}

	@Override
	public void dbCreate() {
		AccountTable table = new AccountTable();

		injectTableByEntity(table, accountData);

		table.id = accountTableDao.insert(table);
		accountData.setAccountId(table.id);

		injectTableByTable(table, accountTableBackup);
	}

	@Override
	public void dbSave() {

		AccountTable newTable = new AccountTable();
		injectTableByEntity(newTable, accountData);

		if (isDiff(newTable, accountTableBackup)) {
			injectTableByTable(newTable, accountTableBackup);
			accountTableDao.update(accountTableBackup);
		}

	}

	private void injectTableByEntity(AccountTable table, IAccountData accountData) {
		table.account = accountData.getAccount();
		table.name = accountData.getName();
		table.id = accountData.getAccountId();
	}

	private void injectEntityByTable(IAccountData accountData, AccountTable table) {
		accountData.setAccount(table.account);
		accountData.setAccountId(table.id);
		accountData.setName(table.name);
	}

	private void injectTableByTable(AccountTable source, AccountTable target) {
		target.account = source.account;
		target.id = source.id;
		target.name = source.name;
	}

	private boolean isDiff(AccountTable source, AccountTable target) {
		if (source.account != target.account)
			return true;
		if (source.name != target.name)
			return true;
		if (source.id != target.id)
			return true;
		return false;
	}

	@Override
	public void dbLoad() {
		// DBManager.loadTable(AccountTable.class, accountData, "findByAccount", new
		// Class<?>[] { String.class }, new Object[] { player.getAccount() });

		AccountTable sourceTable = accountTableDao.findByAccount(player.getAccount());

		injectTableByEntity(accountTableBackup, accountData);
		injectEntityByTable(accountData, sourceTable);

	}

	public void resetData() {
		accountTableBackup.account = null;
		accountTableBackup.name = null;
		accountTableBackup.id = 0;
	}

	public boolean checkAccount(String account) {
		Integer id = accountTableDao.findIdByAccount(account);
		return id != null;
	}
}
