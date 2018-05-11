package org.aimfd.world.player.account.manager.module;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.aimfd.base.ExecutorCenter;
import org.aimfd.world.PlayerCache;
import org.aimfd.world.player.Player;
import org.aimfd.world.player.account.data.IAccountData;

public class LoginModule {

	private Player player;
	private IAccountData accountData;

	public LoginModule(Player player) {
		this.player = player;
		this.accountData = player.getPlayerAllData().getPeaceData().getAccountData();
	}

	public void login(String account, String name, int age) {
		if (account == null || account.trim().equals("")) {
			return;
		}

		ExecutorCenter.loginExecutor.execute(account.hashCode(), new Runnable() {

			@Override
			public void run() {
				// 检查帐号是否存在，不存在就注册
				if (checkAccount(account)) {
					// 账号创建
					player.setAccount(account);
					accountData.setAccount(account);
					accountData.setName(name);
					player.initLogger();

					player.dbCreate();
				} else {
					// 已有账号
					// 已经登录就把登陆的下线
					offline(account);
					// 如果账号存在则加载
					player.setAccount(account);
					player.dbLoad();
				}

				// 玩家上线处理
				player.online();
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

	/**
	 * 检查帐号是否存在
	 * 
	 * @param account
	 * @return
	 */
	private boolean checkAccount(String account) {
		return true;
	}
}
