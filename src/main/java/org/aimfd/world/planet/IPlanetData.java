package org.aimfd.world.planet;

import org.aimfd.world.planet.enterprise.data.IEnterpriseData;
import org.aimfd.world.planet.environment.data.IEnvironmentData;
import org.aimfd.world.planet.terrain.data.ITerrainData;

/**
 * 星球数据
 * 
 * @author AIM
 *
 */
public interface IPlanetData {
	/**
	 * 获取环境数据
	 * 
	 * @return
	 */
	IEnvironmentData getEnviromentData();

	/**
	 * 获取企业数据
	 * 
	 * @return
	 */
	IEnterpriseData getEnterpriseData();

	/**
	 * 获取地图数据
	 * 
	 * @return
	 */
	ITerrainData getTerrainData();
}
