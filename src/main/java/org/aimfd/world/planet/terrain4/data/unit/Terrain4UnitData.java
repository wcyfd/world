package org.aimfd.world.planet.terrain4.data.unit;

public class Terrain4UnitData extends Terrain4UnitDataCodec implements ITerrain4UnitData, ITerrain4ReadData {
	private ITerrain4UnitData left;
	private ITerrain4UnitData right;
	private ITerrain4UnitData top;
	private ITerrain4UnitData bottom;
	private int id;

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public ITerrain4UnitData left() {
		return left;
	}

	@Override
	public void left(ITerrain4UnitData unitData) {
		this.left = unitData;
	}

	@Override
	public ITerrain4UnitData right() {
		return right;
	}

	@Override
	public void right(ITerrain4UnitData unitData) {
		this.right = unitData;
	}

	@Override
	public ITerrain4UnitData top() {
		return top;
	}

	@Override
	public void top(ITerrain4UnitData unitData) {
		this.top = unitData;
	}

	@Override
	public ITerrain4UnitData bottom() {
		return bottom;
	}

	@Override
	public void bottom(ITerrain4UnitData unitData) {
		this.bottom = unitData;
	}

}
