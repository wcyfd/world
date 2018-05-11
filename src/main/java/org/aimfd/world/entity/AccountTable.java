package org.aimfd.world.entity;

import org.aimfd.base.AbstractTable;
import org.aimfd.base.Column;
import org.aimfd.base.Table;
import org.aimfd.world.dao.AccountTableDao;

@Table(AccountTableDao.class)
public class AccountTable extends AbstractTable {

	@Column(value = "setAccountId", paramType = int.class, primaryKey = true)
	public int id;
	@Column(value = "setAccount", paramType = String.class)
	public String account;
	@Column(value = "setName", paramType = String.class)
	public String name;

	@Override
	public String toString() {
		return "AccountTable [account=" + account + ", name=" + name + ", id=" + id + "]";
	}

}
