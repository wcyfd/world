package org.aimfd.world.planet.aoi.manager.module.grid;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.aimfd.world.PlayerCache;
import org.aimfd.world.planet.Planet;
import org.aimfd.world.planet.aoi.data.IAOIGridData;
import org.aimfd.world.planet.aoi.data.unit.IAOIGridUnitData;
import org.aimfd.world.planet.roles.data.IRolesData;
import org.aimfd.world.player.Player;

public class AOIGridLocationModule {
	private IAOIGridData aoiGridData;
	private IRolesData rolesData;

	public AOIGridLocationModule(Planet planet) {
		aoiGridData = planet.getPlanetAllData().getPlanetData().getAOIData();
		rolesData = planet.getPlanetAllData().getPlanetData().getRolesData();
	}

	/**
	 * 获取该坐标所在的所有网格区域
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public List<Integer> getAOIUnitIdListByCoord(int x, int y) {
		List<Integer> aoiUnitDataIdList = new ArrayList<>(5);
		for (IAOIGridUnitData aoiUnitData : aoiGridData.getAOIUnitDataMap().values()) {

			if (contains(aoiUnitData.getX(), aoiUnitData.getY(), aoiUnitData.getWidth(), aoiUnitData.getHeight(), x, y)) {
				aoiUnitDataIdList.add(aoiUnitData.getId());
			}
		}
		return aoiUnitDataIdList;
	}

	/**
	 * 是否包含
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param sourceX
	 * @param sourceY
	 * @return
	 */
	private boolean contains(int x, int y, int width, int height, int sourceX, int sourceY) {
		if (sourceX >= x && sourceX <= (x + width) && sourceY >= y && sourceY <= (y + height)) {
			return true;
		}

		return false;
	}

	/**
	 * 获取范围内的所有连接id
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public Set<Integer> getRangeClientId(int x, int y) {
		List<Integer> aoiUnitIdList = this.getAOIUnitIdListByCoord(x, y);
		Set<Integer> clientIdSet = new HashSet<>();

		// 获取地图的所有客户端id
		for (int unitId : aoiUnitIdList) {
			IAOIGridUnitData unitData = aoiGridData.getAOIUnitDataMap().get(unitId);
			Set<Integer> roleIdSet = unitData.getRoleIdSet();
			for (int roleId : roleIdSet) {
				String account = rolesData.getRoleData(roleId).getAccount();
				Player player = PlayerCache.getPlayerByAccount(account);
				if (player != null) {
					int clientId = player.getClientId();
					clientIdSet.add(clientId);
				}
			}
		}

		return clientIdSet;
	}
}
