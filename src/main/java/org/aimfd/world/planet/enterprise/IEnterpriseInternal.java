package org.aimfd.world.planet.enterprise;

import java.util.List;

/**
 * 企业接口
 * 
 * @author AIM
 *
 */
public interface IEnterpriseInternal {

	/**
	 * 设置企业
	 * 
	 * @param accounts
	 */
	void applyEnterprises(List<String> accounts);

	/**
	 * 获取玩家客户端id
	 * 
	 * @return
	 */
	List<Integer> getClientIds();
}
