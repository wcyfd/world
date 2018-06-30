package org.aimfd.world.planet.role.data;

import org.aimfd.base.IData;

/**
 * 角色数据
 * 
 * @author AIM
 *
 */
public class RoleData extends RoleDataCodec implements IData, IRoleData {

	public RoleData() {
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
	public void resetData() {
		this.name = null;
		this.account = null;
		this.x = -1;
		this.y = -1;
	}

}
