package org.aimfd.world.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aimfd.world.system.match.ISystemMatchPublic;
import org.aimfd.world.system.match.manager.SystemMatchManager;
import org.aimfd.world.system.room.ISystemRoomPublic;
import org.aimfd.world.system.room.manager.SystemRoomManager;

public class ASystem {

	private SystemAllData systemAllData;
	private Map<Class<?>, SystemManager> systemInterfaces;
	private List<SystemManager> systemManagers;

	public ASystem() {
		systemAllData = new SystemAllData();
		systemInterfaces = new HashMap<>();
		systemManagers = new ArrayList<>();

		registSystemManagers();
	}

	protected void registSystemManagers() {
		registSystemManager(SystemMatchManager.class, ISystemMatchPublic.class);
		registSystemManager(SystemRoomManager.class, ISystemRoomPublic.class);
	}

	/**
	 * 注册系统模块
	 * 
	 * @param systemManagerClazz
	 * @param interfaceClasses
	 */
	protected void registSystemManager(Class<? extends SystemManager> systemManagerClazz, Class<?>... interfaceClasses) {

		try {
			SystemManager systemManager = systemManagerClazz.newInstance();
			systemManagers.add(systemManager);

			for (Class<?> clazz : interfaceClasses) {
				systemInterfaces.put(clazz, systemManager);
				SystemICenter.registInterface(clazz, systemManager);
			}
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}

	}

	public SystemAllData getSystemAllData() {
		return systemAllData;
	}

	public void init() {
		for (SystemManager systemManager : systemManagers) {
			systemManager.init();
		}
	}

	@SuppressWarnings("unchecked")
	public <T extends SystemManager> T getSystemManager(Class<?> clazz) {
		SystemManager systemManager = systemInterfaces.get(clazz);
		return (T) systemManager;
	}
}
