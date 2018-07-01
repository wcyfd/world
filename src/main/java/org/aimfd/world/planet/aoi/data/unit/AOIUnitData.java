package org.aimfd.world.planet.aoi.data.unit;

import java.util.HashSet;

import org.aimfd.base.IData;

public class AOIUnitData extends AOIUnitDataCodec implements IData, IAOIUnitData {

	public AOIUnitData() {
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

}
