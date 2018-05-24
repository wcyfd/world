package org.aimfd.world.player.account.data;

import org.aimfd.base.IData;

public class AccountData extends AccountDataCodec implements IData, IAccountData {

	private int accountId;

	@Override
	public void resetData() {
		accountId = -1;
		account = null;
		name = null;
	}

	@Override
	public String getAccount() {
		return account;
	}

	@Override
	public void setAccount(String account) {
		this.account = account;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int getAccountId() {
		return accountId;
	}

	@Override
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

}
