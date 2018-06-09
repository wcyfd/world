package org.aimfd.world;

import java.util.HashMap;
import java.util.Map;

import org.aimfd.world.player.Player;

public class PlayerICenter {
	private final static Map<Integer, Player> playerMap = new HashMap<>();

	public static <T> T getInterface(int clientId, Class<T> clazz) {
		return playerMap.get(clientId).getPlayerManager(clazz);
	}

	public static void registPlayer(Player player) {
		playerMap.put(player.getClientId(), player);
	}
}
