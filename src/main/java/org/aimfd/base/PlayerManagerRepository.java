package org.aimfd.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.aimfd.world.player.PlayerManager;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class PlayerManagerRepository implements ApplicationContextAware {

	// 存放所有玩家的管理类
	private static final List<Class<? extends PlayerManager>> playerManagerClasses = new ArrayList<>();

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		Map<String, PlayerManager> playerManagers = applicationContext.getBeansOfType(PlayerManager.class);
		for (PlayerManager playerManager : playerManagers.values()) {
			playerManagerClasses.add(playerManager.getClass());
		}
	}

	public static List<Class<? extends PlayerManager>> getPlayerManagerClasses() {
		return playerManagerClasses;
	}
}
