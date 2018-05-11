package org.aimfd.world.planet.enterprise.data.unit;

public interface IEnterpriseUnitData {

	/**
	 * 获取企业id
	 * 
	 * @return
	 */
	int getId();

	/**
	 * 获取账号
	 * 
	 * @return
	 */
	String getAccount();

	/**
	 * 设置帐号
	 * 
	 * @param account
	 */
	void setAccount(String account);

	/**
	 * 获取开发率
	 * 
	 * @return
	 */
	int getTerraformRate();

	/**
	 * 设置开发率
	 * 
	 * @param rate
	 */
	void setTerraformRate(int rate);

	/**
	 * 获取奖励点
	 * 
	 * @return
	 */
	int getBonus();

	/**
	 * 设置奖励点
	 * 
	 * @param bonus
	 */
	void setBonus(int bonus);
}
