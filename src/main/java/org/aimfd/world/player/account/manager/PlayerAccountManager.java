package org.aimfd.world.player.account.manager;

import org.aimfd.base.IDBAPI;
import org.aimfd.world.player.PlayerManager;
import org.aimfd.world.player.account.IAccountPublic;
import org.aimfd.world.player.account.db.AccountDB;
import org.aimfd.world.player.account.manager.module.LoginModule;

public class PlayerAccountManager extends PlayerManager implements IAccountPublic, IDBAPI {

	private LoginModule loginModule;
	private AccountDB accountDB;

	@Override
	public void init() {
		accountDB = new AccountDB(player);
		loginModule = new LoginModule(player, accountDB);
	}

	@Override
	public void dbCreate() {
		accountDB.dbCreate();
	}

	@Override
	public void dbSave() {
		accountDB.dbSave();
	}

	@Override
	public void dbLoad() {
		accountDB.dbLoad();
	}

	@Override
	public void login(String account, String name, int age) {
		loginModule.login(account, name, age);
	}

}
