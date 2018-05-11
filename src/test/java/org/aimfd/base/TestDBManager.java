package org.aimfd.base;

import org.aimfd.world.entity.AccountTable;
import org.aimfd.world.player.account.data.AccountData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDBManager {
	private AccountData accountData;

	@Before
	public void pre() {
		accountData = new AccountData();
	}

	@Test
	public void dbTest() {
		DBManager.loadTable(AccountTable.class, accountData, 1);
	}
}
