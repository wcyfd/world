package org.aimfd.world.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aimfd.base.IDBAPI;
import org.aimfd.base.LoggerPrefix;
import org.aimfd.base.LoggerPrefixFactory;
import org.aimfd.base.Route;
import org.aimfd.world.player.account.IAccountInternal;
import org.aimfd.world.player.account.IAccountPublic;
import org.aimfd.world.player.account.manager.PlayerAccountManager;
import org.aimfd.world.player.planet.IPlanetInternal;
import org.aimfd.world.player.planet.IPlanetPublic;
import org.aimfd.world.player.planet.manager.PlayerPlanetManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.Channel;

public class Player {
	static {
		LoggerFactory.getLogger(Player.class);
	}
	protected final LoggerPrefix logger;
	private final int clientId;
	private String account;
	private PlayerAllData playerAllData;
	private boolean isLogin;// 帐号是否登录

	private Map<Class<?>, PlayerManager> playerInterfaces = new HashMap<>();
	private List<PlayerManager> playerManagers = new ArrayList<>();

	public Player(int clientId) {
		logger = LoggerPrefixFactory.getLogger(Player.class);
		this.clientId = clientId;
		this.playerAllData = new PlayerAllData();

		initLogger();
		registPlayerManagers();
		logger.info("初始化玩家容器完毕");
	}

	public void initPlayer(Channel channel) {
		Route.initChannel(clientId, channel);
	}

	public void initLogger() {
		logger.setPrefix("[" + clientId + ":" + account + "]");
	}

	public Logger getLogger() {
		return logger;
	}

	/**
	 * 注册所有玩家的管理模块
	 */
	private void registPlayerManagers() {
		registPlayerManager(PlayerAccountManager.class, IAccountInternal.class, IAccountPublic.class);
		registPlayerManager(PlayerPlanetManager.class, IPlanetInternal.class, IPlanetPublic.class);
	}

	protected void registPlayerManager(Class<? extends PlayerManager> playerManagerClazz, Class<?>... interfaceClasses) {
		try {
			PlayerManager playerManager = playerManagerClazz.newInstance();
			playerManager.player = this;

			playerManagers.add(playerManager);

			if (interfaceClasses != null) {
				for (Class<?> clazz : interfaceClasses) {
					playerInterfaces.put(clazz, playerManager);
				}
			}

		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public int getClientId() {
		return clientId;
	}

	public PlayerAllData getPlayerAllData() {
		return playerAllData;
	}

	@SuppressWarnings("unchecked")
	public <T> T getPlayerManager(Class<T> clazz) {
		return (T) playerInterfaces.get(clazz);
	}

	public void init() {
		// 玩家管理器初始化
		for (PlayerManager playerManager : playerInterfaces.values()) {
			playerManager.init();
		}
	}

	public void dbCreate() {
		for (PlayerManager playerManager : playerInterfaces.values()) {
			if (playerManager instanceof IDBAPI) {
				((IDBAPI) playerManager).dbCreate();
			}
		}
	}

	public void dbSave() {
		for (PlayerManager playerManager : playerInterfaces.values()) {
			if (playerManager instanceof IDBAPI) {
				((IDBAPI) playerManager).dbSave();
			}
		}
	}

	public void dbLoad() {
		for (PlayerManager playerManager : playerInterfaces.values()) {
			if (playerManager instanceof IDBAPI) {
				((IDBAPI) playerManager).dbLoad();
			}
		}
	}

	public void online() {
		for (PlayerManager playerManager : playerInterfaces.values()) {
			playerManager.online();
		}
	}

	public void offline() {
		for (PlayerManager playerManager : playerInterfaces.values()) {
			playerManager.offline();
		}

		this.resetData();
	}

	public void resetData() {
		account = null;// 帐号重置
		logger.setPrefix(null);
		playerAllData.resetData();// 重置数据
		Route.resetChannel(clientId);// 重置通信通道
	}

	public boolean isLogin() {
		return isLogin;
	}

	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

}
