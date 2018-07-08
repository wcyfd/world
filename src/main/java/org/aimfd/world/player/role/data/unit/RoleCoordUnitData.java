package org.aimfd.world.player.role.data.unit;

import org.aimfd.base.IData;

/**
 * 角色坐标数据
 * 
 * @author AIM
 *
 */
public class RoleCoordUnitData extends RoleCoordUnitDataCodec implements IData, IRoleCoordUnitData {

	@Override
	public int getRoleId() {
		return roleId;
	}

	@Override
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public void setX(int x) {
		this.x = x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public void resetData() {
		x = -1;
		y = -1;
		roleId = -1;
	}

}
