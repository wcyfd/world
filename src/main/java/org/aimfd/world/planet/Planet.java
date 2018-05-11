package org.aimfd.world.planet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aimfd.base.LoggerPrefix;
import org.aimfd.base.LoggerPrefixFactory;
import org.aimfd.base.SpringContext;
import org.aimfd.world.planet.enterprise.IEnterprisePublic;
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
	 * 初始化游戏
	 * 
	 * @param enterpriseCount
	 */
	public void initPlanet(int enterpriseCount) {
		this.getPlanetManagerInterface(IEnterprisePublic.class).initEnterpriseCount(enterpriseCount);
	}

	/**
	 * 初始化
	 */
	public void init() {
		for (PlanetManager planetManager : planetManagers) {
			planetManager.init();
		}
	}

	public void start() {
		for (PlanetManager planetManager : planetManagers) {
			planetManager.onStart();
		}
	}

	public void enter() {
		for (PlanetManager planetManager : planetManagers) {
			planetManager.onEnter();
		}
	}

	public void exit() {
		for (PlanetManager planetManager : planetManagers) {
			planetManager.onExit();
		}
	}

	public void update() {
		for (PlanetManager planetManager : planetManagers) {
			planetManager.update();
		}
	}

	/**
	 * 注册星球的管理器
	 */
	public void registerPlanetManager() {
		Map<String, Object> planetManagersTemplate = SpringContext.getContext().getBeansWithAnnotation(PlanetManagerRegistry.class);
		for (Object manager : planetManagersTemplate.values()) {
			PlanetManager planetManagerTemplate = (PlanetManager) manager;
			Class<? extends PlanetManager> planetManagerClazz = planetManagerTemplate.getClass();
			try {
				PlanetManager planetManager = planetManagerClazz.newInstance();
				planetManager.planet = this;

				planetManagers.add(planetManager);

				PlanetManagerRegistry planetAnnotation = planetManager.getClass().getAnnotation(PlanetManagerRegistry.class);
				for (Class<?> planetManagerInterface : planetAnnotation.value()) {
					planetInterfaceManagers.put(planetManagerInterface, planetManager);
				}
			} catch (InstantiationException | IllegalAccessException e) {
				this.logger.error("初始化错误 ", e);
			}
		}
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
