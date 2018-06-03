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
		logger.setPrefix("[" + account + "]:[" + clientId + "]");
		logger.info("init logger");
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
		for (PlayerManager playerManager : playerManagers) {
			playerManager.init();
		}
	}

	public void dbCreate() {
		for (PlayerManager playerManager : playerManagers) {
			if (playerManager instanceof IDBAPI) {
				((IDBAPI) playerManager).dbCreate();
			}
		}
		logger.info("玩家创建完成");
	}

	public void dbSave() {
		for (PlayerManager playerManager : playerManagers) {
			if (playerManager instanceof IDBAPI) {
				((IDBAPI) playerManager).dbSave();
			}
		}
		logger.info("数据保存完毕");
	}

	public void dbLoad() {
		for (PlayerManager playerManager : playerManagers) {
			if (playerManager instanceof IDBAPI) {
				((IDBAPI) playerManager).dbLoad();
			}
		}

		logger.info("数据加载完成");
	}

	public void online() {
		for (PlayerManager playerManager : playerManagers) {
			playerManager.online();
		}
		isLogin = true;
		logger.info("玩家上线完成");
	}

	/**
	 * 数据保存，数据清空
	 */
	public void offline() {
		// 下线操作
		for (PlayerManager playerManager : playerManagers) {
			playerManager.offline();
		}
		isLogin = false;
		logger.info("玩家下线完成");
	}

	/**
	 * 重置数据
	 */
	public void resetData() {
		// 管理器临时数据重置
		for (PlayerManager playerManager : playerManagers) {
			playerManager.resetData();
		}

		// 玩家静态数据清空
		playerAllData.resetData();

		logger.info("数据清空完成");

		account = null;// 帐号重置
		logger.info("clear logger");
		logger.setPrefix(null);// 重置日志工具

		Route.resetChannel(clientId);// 重置通信通道
		logger.info("最后清空完成");
	}

	public boolean isLogin() {
		return isLogin;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

}
