package org.aimfd.world;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("org.aimfd")
@MapperScan("org.aimfd.world.dao")
public class WorldApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(WorldApplication.class);
		app.addListeners(new SpringListener());
		app.run(args);
//		// AccountTableDao accountTableDao =
//		// SpringContext.getContext().getBean(AccountTableDao.class);
//		// AccountTable table = accountTableDao.findByAccount("wcyaccount");
//		// System.out.println(table);
//
//		AccountData accountData = new AccountData();
//		// DBManager.loadTable(AccountTable.class, accountData, 2);
//		// System.out.println(accountData);
//		//
//		// accountData.resetData();
//		// DBManager.loadTable(AccountTable.class, accountData, "findByAccount", new
//		// Class<?>[] { String.class }, new Object[] { "wcyaccount" });
//		System.out.println(accountData);
//
//		DBManager.loadTable(AccountTable.class, accountData, "findByAccount", new Class<?>[] { String.class }, new Object[] { "wcyaccount" }, new DBDataLoader<AccountTable, AccountData>() {
//
//			@Override
//			public void load(AccountTable table, AccountData entity) {
//				entity.setAccount(table.account);
//				entity.setAccountId(table.id);
//				entity.setName(table.name);
//			}
//
//		});
//		System.out.println(accountData);
//
//		List<AccountData> list = new ArrayList<>();
//		DBManager.loadTables(AccountTable.class, list, AccountData.class, "findAll", new Class<?>[] { String.class }, new Object[] { "wcyaccount" });
//		System.out.println(list);

	}
}
