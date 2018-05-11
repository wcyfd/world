package org.aimfd.world.dao;

import java.util.List;

import org.aimfd.base.BaseDao;
import org.aimfd.world.entity.AccountTable;

public interface AccountTableDao extends BaseDao<AccountTable, Integer> {
	Integer getIdByAccount(String account);

	AccountTable findByAccount(String account);

	List<AccountTable> findAll(String account);
}
