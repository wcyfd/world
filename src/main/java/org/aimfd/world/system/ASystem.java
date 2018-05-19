package org.aimfd.world.system;

import java.util.HashMap;
import java.util.Map;

import org.aimfd.base.SpringContext;
import org.aimfd.base.SystemManagerRegistry;

public class ASystem {
	static {
		_init();
	}

	private static SystemAllData systemAllData;
	private static Map<Class<?>, SystemManager> systemInterfaces;

	private static void _init() {
		systemAllData = new SystemAllData();
		systemInterfaces = new HashMap<>();
		
		registManagers();
	}

	private static void registManagers() {
		Map<String, Object> systemManagerTemplates = SpringContext.getContext().getBeansWithAnnotation(SystemManagerRegistry.class);
		for (Map.Entry<String, Object> entrySet : systemManagerTemplates.entrySet()) {

			SystemManager systemManager = (SystemManager) entrySet.getValue();
			Class<?> systemManagerClazz = systemManager.getClass();

			SystemManagerRegistry registry = systemManagerClazz.getAnnotation(SystemManagerRegistry.class);
			Class<?>[] classes = registry.value();

			for (Class<?> clazz : classes) {
				systemInterfaces.put(clazz, systemManager);
			}
		}
	}

	public static SystemAllData getSystemAlLData() {
		return systemAllData;
	}

	public static void init() {
		Map<String, Object> systemManagerTemplates = SpringContext.getContext().getBeansWithAnnotation(SystemManagerRegistry.class);
		for (Map.Entry<String, Object> entrySet : systemManagerTemplates.entrySet()) {
			SystemManager systemManager = (SystemManager) entrySet.getValue();
			systemManager.init();
		}
	}

	@SuppressWarnings("unchecked")
	public static <T extends SystemManager> T getSystemManager(Class<?> clazz) {
		SystemManager systemManager = systemInterfaces.get(clazz);
		return (T) systemManager;
	}
}
