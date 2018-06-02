package org.aimfd.world.player.account.manager.module;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.aimfd.base.ExecutorCenter;
import org.aimfd.world.PlayerCache;
import org.aimfd.world.handler.AccountHandler;
import org.aimfd.world.player.Player;
import org.aimfd.world.player.account.data.IAccountData;
import org.aimfd.world.player.account.db.AccountDB;
import org.slf4j.Logger;

public class LoginModule {

	private Player player;
	private IAccountData accountData;
	private AccountDB accountDB;
	private Logger logger;

	public LoginModule(Player player, AccountDB accountDB) {
		this.player = player;
		this.accountData = player.getPlayerAllData().getPeaceData().getAccountData();
		this.accountDB = accountDB;
		this.logger = player.getLogger();
	}

	public void login(String account, String name, int age) {

		if (account == null || account.trim().equals("")) {
			return;
		}

		ExecutorCenter.loginExecutor.execute(account.hashCode(), new Runnable() {

			@Override
			public void run() {
				// 检查帐号是否存在，不存在就注册
				if (!accountDB.checkAccount(account)) {
					// 账号创建
					player.setAccount(account);
					accountData.setAccount(account);
					accountData.setName(name);
					player.initLogger();

					player.dbCreate();
				} else {
					// 已有账号
					logger.debug("顶号处理:{}", player.getAccount());
					// 已经登录就把登陆的下线
					offline(account);
					// 如果账号存在则加载
					player.setAccount(account);
					player.dbLoad();
				}

				// 玩家上线处理
				player.online();

				AccountHandler.responseLogin(player.getClientId(), player.getPlayerAllData().getPeaceData());
			}

		});
	}

	private void offline(String account) {
		// 使用逻辑线程将玩家下线
		Future<?> future = ExecutorCenter.logicExecutor.submit(account.hashCode(), new Runnable() {

			@Override
			public void run() {
				// 获取已经上线的位置
				Player player = PlayerCache.getPlayerByAccount(account);
				if (player != null) {
					logger.debug("原玩家下线，clientId={}", player.getClientId());
					player.offline();
				}
			}

		});

		try {
			future.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

}
