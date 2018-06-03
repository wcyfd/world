package org.aimfd.world.player.account.manager.module;

import org.aimfd.base.ExecutorCenter;
import org.aimfd.base.GameSocket;
import org.aimfd.base.Route;
import org.aimfd.world.PlayerCache;
import org.aimfd.world.handler.AccountHandler;
import org.aimfd.world.player.Player;
import org.aimfd.world.player.account.data.IAccountData;
import org.aimfd.world.player.account.db.AccountDB;
import org.slf4j.Logger;

import io.netty.channel.Channel;
import io.netty.channel.ChannelPromise;

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
					accountData.setName(name);
					accountData.setAccount(account);

					player.initLogger();

					player.dbCreate();
				} else {

					// 已经登录就把登陆的下线
					offline(account);
					// 如果账号存在则加载
					player.setAccount(account);
					player.initLogger();
					player.dbLoad();
				}

				// 玩家上线处理
				player.online();

				AccountHandler.responseLogin(player.getClientId(), player.getPlayerAllData().getPeaceData());
			}

		});
	}

	private void offline(String account) {

		Player player = PlayerCache.getPlayerByAccount(account);
		if (player == null) {
			return;
		}

		// 已有账号
		logger.info("顶号处理:{}", player.getAccount());

		Channel channel = Route.getChannel(player.getClientId());

		ChannelPromise promise = channel.newPromise();
		channel.attr(GameSocket.CHANNEL_PROMISE_KEY).set(promise);
		try {
			channel.close(promise);
		} catch (Exception e) {
			player.getLogger().error("断开异常", e);
		} finally {
			channel.attr(GameSocket.CHANNEL_PROMISE_KEY).set(null);
		}

	}

}
