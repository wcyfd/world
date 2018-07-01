package org.aimfd.world.player.role.data;

import org.aimfd.base.IData;

public class RoleData extends RoleDataCodec implements IRoleData, IData {
	private int roleId;

	public RoleData() {
		this.roleId = -1;
		this.name = null;
		this.account = null;
		this.x = -1;
		this.y = -1;
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
	public void setX(int x) {
		this.x = x;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void resetData() {
		this.roleId = -1;
		this.name = null;
		this.account = null;
		this.x = -1;
		this.y = -1;
	}
}
