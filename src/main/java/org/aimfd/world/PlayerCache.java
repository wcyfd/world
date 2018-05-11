package org.aimfd.world;

import java.util.HashMap;
import java.util.Map;

import org.aimfd.world.player.Player;

public class PlayerCache {
	private static final Map<Integer, Player> playerCache = new HashMap<>();

	public static void addPlayer(Player player) {
		int clientId = player.getClientId();
		playerCache.put(clientId, player);
	}

	public static Player getPlayerByClientId(Integer clientId) {
		if (clientId == null) {
			return null;
		}
		return playerCache.get(clientId);
	}

	/**
	 * 根据账号获取玩家
	 * 
	 * @param account
	 * @return
	 */
	public static Player getPlayerByAccount(String account) {
		for (Player player : playerCache.values()) {
			if (player.isLogin()) {// 已经登陆则检查
				if (player.getAccount().equals(account)) {
					return player;
				}
				break;
			}
		}
		return null;
	}

}
