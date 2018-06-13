package org.aimfd.world.planet.enterprise.manager.module;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.aimfd.world.PlayerCache;
import org.aimfd.world.planet.Planet;
import org.aimfd.world.planet.enterprise.data.IEnterpriseData;
import org.aimfd.world.planet.enterprise.data.unit.IEnterpriseUnitData;
import org.aimfd.world.player.Player;

public class EnterpriseInitModule {
	private IEnterpriseData enterpriseData;

	public EnterpriseInitModule(Planet planet) {
		this.enterpriseData = planet.getPlanetAllData().getPlanetData().getEnterpriseData();
	}

	/**
	 * 参与的玩家正在申请企业
	 * 
	 * @param accounts
	 */
	public void applyEnterprises(List<String> accounts) {
		for (String account : accounts) {
			applyEnterprise(account);
		}
	}

	/**
	 * 申请企业
	 * 
	 * @param account
	 */
	private void applyEnterprise(String account) {
		Map<String, Integer> accountMap = enterpriseData.getAccounts();
		Map<Integer, IEnterpriseUnitData> unitDataMap = enterpriseData.getUnitData();
		int size = accountMap.size();
		IEnterpriseUnitData unitData = unitDataMap.get(size);
		// 如果包含了这个账号，则不进行申请
		if (accountMap.containsKey(account)) {
			return;
		}
		unitData.setAccount(account);
		accountMap.put(account, unitData.getId());
	}

	/**
	 * 获取客户端id列表
	 * 
	 * @return
	 */
	public List<Integer> getClientIds() {
		Map<String, Integer> accounts = enterpriseData.getAccounts();
		List<Integer> clientIds = new ArrayList<>(accounts.size());
		for (String account : accounts.keySet()) {
			Player player = PlayerCache.getPlayerByAccount(account);
			if (player == null) {
				continue;
			}

			clientIds.add(player.getClientId());
		}
		return clientIds;
	}

	/**
	 * 根据帐号获取企业id
	 * 
	 * @param account
	 * @return
	 */
	public int getEnterpriseId(String account) {
		Integer enterpriseId = enterpriseData.getAccounts().get(account);
		if (enterpriseId == null) {
			return -1;
		}
		return enterpriseId;
	}

}
