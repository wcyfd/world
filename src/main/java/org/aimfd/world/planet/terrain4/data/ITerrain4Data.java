package org.aimfd.world.planet.terrain4.data;

import org.aimfd.world.planet.terrain4.data.unit.ITerrain4UnitData;

public interface ITerrain4Data {
	void setWidth(int width);

	void setHeight(int height);

	int getWidth();

	int getHeight();

	ITerrain4UnitData getITerrain4Data(int id);
}
