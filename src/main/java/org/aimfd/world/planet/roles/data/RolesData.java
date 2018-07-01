package org.aimfd.world.planet.roles.data;

import java.util.HashMap;

import org.aimfd.base.IData;
import org.aimfd.world.player.role.data.IRoleData;

/**
 * 场景角色
 * 
 * @author AIM
 *
 */
public class RolesData extends RolesDataCodec implements IData, IRolesData {

	public RolesData() {
		this.roleDataMap = new HashMap<>();
	}

	@Override
	public IRoleData getRoleData(int id) {
		return roleDataMap.get(id);
	}

	@Override
	public void resetData() {
		this.roleDataMap.clear();
	}

	@Override
	public void addRoleData(IRoleData roleData) {
		this.roleDataMap.put(roleData.getRoleId(), roleData);
	}

	@Override
	public void removeRoleData(int roleId) {
		this.roleDataMap.remove(roleId);
	}

}
