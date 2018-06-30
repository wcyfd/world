package org.aimfd.world.planet.aoi.data.unit;

import java.util.HashMap;
import java.util.Map;

import org.aimfd.base.IData;
import org.aimfd.world.planet.role.data.IRoleData;

/**
 * AOI单元
 * 
 * @author AIM
 *
 */
public class AOIUnitData extends AOIUnitDataCodec implements IData, IAOIUnitData {

	public AOIUnitData() {
		this.roleDataMap = new HashMap<>();
		this.id = -1;
	}

	@Override
	public Map<String, IRoleData> getRoleDataMap() {
		return roleDataMap;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void resetData() {
		this.id = -1;
		for (IRoleData roleData : roleDataMap.values()) {
			IData data = (IData) roleData;
			data.resetData();
		}
	}

}
