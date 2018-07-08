package org.aimfd.world.player.role.data;

import org.aimfd.base.IData;
import org.aimfd.world.player.role.data.unit.IRoleCoordUnitData;
import org.aimfd.world.player.role.data.unit.RoleCoordUnitData;

public class RoleData extends RoleDataCodec implements IRoleData, IData {

	public RoleData() {
		this.roleId = -1;
		this.name = null;
		this.account = null;
		this.coordData = new RoleCoordUnitData();
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setAccount(String account) {
		this.account = account;
	}

	@Override
	public String getAccount() {
		return account;
	}

	@Override
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	@Override
	public int getRoleId() {
		return roleId;
	}

	@Override
	public IRoleCoordUnitData getCoord() {
		return coordData;
	}

	@Override
	public void resetData() {
		this.roleId = -1;
		this.name = null;
		this.account = null;
		((IData) coordData).resetData();
	}

}
