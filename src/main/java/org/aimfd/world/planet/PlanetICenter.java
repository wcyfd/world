package org.aimfd.world.planet;

import org.aimfd.world.PlanetCache;

/**
 * 星球接口类
 * 
 * @author AIM
 *
 */
public class PlanetICenter {
	public static <T> T getInterface(int planetId, Class<T> clazz) {
		return PlanetCache.getPlanetById(planetId).getPlanetManagerInterface(clazz);
	}
}
