package org.aimfd.world.planet.aoi.data.grid.unit;

import java.util.HashSet;
import java.util.Set;

import org.aimfd.base.IData;

public class AOIGridUnitData extends AOIGridUnitDataCodec implements IData, IAOIGridUnitData {

	public AOIGridUnitData() {
		this.id = -1;
		this.roleIdSet = new HashSet<>();
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
		this.roleIdSet.clear();
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
	public void setWidth(int width) {
		this.width = width;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public Set<Integer> getRoleIdSet() {
		return roleIdSet;
	}
}
