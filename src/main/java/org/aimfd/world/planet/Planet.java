package org.aimfd.world.planet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aimfd.base.LoggerPrefix;
import org.aimfd.base.LoggerPrefixFactory;
import org.aimfd.world.planet.enterprise.IEnterpriseInternal;
import org.aimfd.world.planet.enterprise.IEnterprisePublic;
import org.aimfd.world.planet.enterprise.manager.PlanetEnterpriseManager;
import org.aimfd.world.planet.environment.IEnvironmentPublic;
import org.aimfd.world.planet.environment.manager.PlanetEnvironmentManager;
import org.aimfd.world.planet.terrain.ITerrainPublic;
import org.aimfd.world.planet.terrain.manager.PlanetTerrainManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Planet {
	static {
		LoggerFactory.getLogger(Planet.class);
	}
	protected final LoggerPrefix logger;

	private final int planetId;
	private PlanetAllData planetAllData;

	private Map<Class<?>, PlanetManager> planetInterfaceManagers = new HashMap<>();
	private List<PlanetManager> planetManagers = new ArrayList<>();

	public Planet(int planetId) {
		this.planetId = planetId;
		this.planetAllData = new PlanetAllData();
		this.logger = LoggerPrefixFactory.getLogger(Planet.class);

		registPlanetManagers();
	}

	public int getPlanetId() {
		return planetId;
	}

	/**
	 * 设置逻辑名称用于区分每一场游戏
	 * 
	 * @param planetLogicName
	 */
	public void setLogicName(String planetLogicName) {
		this.logger.setPrefix("[" + planetId + ":" + planetLogicName + "]");
	}

	/**
	 * 获取日志记录器
	 * 
	 * @return
	 */
	public Logger logger() {
		return logger;
	}

	/**
	 * 初始化
	 */
	public void init() {
		for (PlanetManager planetManager : planetManagers) {
			planetManager.init();
		}
	}

	/**
	 * 模块初始化时的统一操作
	 */
	public void start() {
		for (PlanetManager planetManager : planetManagers) {
			planetManager.onStart();
		}
	}

	public void end() {
		for (PlanetManager planetManager : planetManagers) {
			planetManager.onEnd();
		}
	}

	public void update() {
		for (PlanetManager planetManager : planetManagers) {
			planetManager.onUpdate();
		}
	}

	/**
	 * 注册星球的管理器
	 */
	private void registPlanetManager(Class<? extends PlanetManager> clazz, Class<?>... interfaceClasses) {
		try {
			PlanetManager planetManager = clazz.newInstance();
			planetManager.planet = this;

			planetManagers.add(planetManager);

			for (Class<?> planetManagerInterface : interfaceClasses) {
				planetInterfaceManagers.put(planetManagerInterface, planetManager);
			}
		} catch (InstantiationException | IllegalAccessException e) {
			this.logger.error("初始化错误 ", e);
		}
	}

	protected void registPlanetManagers() {
		registPlanetManager(PlanetEnterpriseManager.class, IEnterprisePublic.class, IEnterpriseInternal.class);
		registPlanetManager(PlanetTerrainManager.class, ITerrainPublic.class);
		registPlanetManager(PlanetEnvironmentManager.class, IEnvironmentPublic.class);
	}

	/**
	 * 通过接口获取管理器
	 * 
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T getPlanetManagerInterface(Class<T> clazz) {
		return (T) planetInterfaceManagers.get(clazz);
	}

	/**
	 * 获得星球的所有数据
	 * 
	 * @return
	 */
	public PlanetAllData getPlanetAllData() {
		return planetAllData;
	}

	public void resetData() {
		this.logger.setPrefix(null);
		planetAllData.resetData();
	}

}
