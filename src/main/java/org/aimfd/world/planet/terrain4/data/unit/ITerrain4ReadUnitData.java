package org.aimfd.world.planet.terrain4.data.unit;

public interface ITerrain4ReadUnitData {
	ITerrain4UnitData left();

	ITerrain4UnitData right();

	ITerrain4UnitData top();

	ITerrain4UnitData bottom();

	int getId();
}
