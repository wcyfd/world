package org.aimfd.world.system;

import java.util.HashMap;
import java.util.Map;

public class SystemICenter {
	private static Map<Class<?>, SystemManager> systemManagers = new HashMap<>();

	@SuppressWarnings("unchecked")
	public static <T> T getSystemInterface(Class<T> clazz) {
		return (T) systemManagers.get(clazz);
	}

	protected static void registInterface(Class<?> clazz, SystemManager systemManager) {
		systemManagers.put(clazz, systemManager);
	}

}
