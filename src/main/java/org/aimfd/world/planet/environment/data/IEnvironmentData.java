package org.aimfd.world.planet.environment.data;

public interface IEnvironmentData {
	/**
	 * 设置氧气量
	 * 
	 * @param oxygen
	 */
	void setOxygen(int oxygen);

	/**
	 * 获得氧气量
	 * 
	 * @return
	 */
	int getOxygen();

	/**
	 * 设置温度
	 * 
	 * @param temperature
	 */
	void setTemperature(int temperature);

	/**
	 * 获得温度
	 * 
	 * @return
	 */
	int getTemperature();
}
