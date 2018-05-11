package org.aimfd.world.planet.terrain.data.unit;

import java.util.HashMap;
import java.util.Map;

import org.aimfd.world.IData;

/**
 * 瓦片单元数据
 * 
 * @author AIM
 *
 */
public class TerrainUnitData extends TerrainUnitDataCodec implements ITerrainUnitData, IData {

	private Map<Integer, ITerrainUnitData> unitMap;

	public TerrainUnitData() {
		unitMap = new HashMap<>();

		resetData();
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public void setEnterpriseId(int enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	@Override
	public int getEnterpriseId() {
		return this.enterpriseId;
	}

	@Override
	public void setType(int type) {
		this.type = type;
	}

	@Override
	public int getType() {
		return type;
	}

	@Override
	public void resetData() {
		this.id = -1;
		this.enterpriseId = -1;
		this.type = -1;

		this.unitMap.clear();
	}

	@Override
	public ITerrainUnitData getTerrainUnit(int location) {
		return unitMap.get(location);
	}

	@Override
	public void bindTerrainUnitData(int locate, ITerrainUnitData unitData) {
		unitMap.put(locate, unitData);
	}

	@Override
	public Map<Integer, ITerrainUnitData> getTerrainUnits() {
		return unitMap;
	}

	@Override
	public String toString() {
		return "TerrainUnitData [id=" + id + "]";
	}

}
