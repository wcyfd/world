package org.aimfd.world.planet.terrain.data.unit;

import java.util.Map;

public interface ITerrainUnitData {
	/**
	 * 设置板块唯一id
	 * 
	 * @param id
	 */
	void setId(int id);

	/**
	 * 获得板块唯一id
	 * 
	 * @return
	 */
	int getId();

	/**
	 * 设置企业id
	 * 
	 * @param enterpriseId
	 */
	void setEnterpriseId(int enterpriseId);

	/**
	 * 获得企业id
	 * 
	 * @return
	 */
	int getEnterpriseId();

	/**
	 * 设置板块类型
	 * 
	 * @param type
	 */
	void setType(int type);

	/**
	 * 获得板块类型
	 * 
	 * @return
	 */
	int getType();

	/**
	 * 左上0,右上1,右2,右下3,左下4,左5
	 * 
	 * @return
	 */
	ITerrainUnitData getTerrainUnit(int location);

	Map<Integer, ITerrainUnitData> getTerrainUnits();

	/**
	 * 绑定位置
	 * 
	 * @param locate
	 * @param unitData
	 */
	void bindTerrainUnitData(int locate, ITerrainUnitData unitData);
}
