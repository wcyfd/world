package org.aimfd.world.planet.aoi.data.link;

import java.util.Map;

import org.aimfd.world.planet.aoi.data.link.unit.TenNodeWrapper;
import org.aimfd.world.player.role.data.unit.IRoleCoordUnitData;

public interface IAOILinkData {
	TenNodeWrapper<IRoleCoordUnitData> getLinkedListX();

	TenNodeWrapper<IRoleCoordUnitData> getLinkedListY();

	Map<Integer, TenNodeWrapper<IRoleCoordUnitData>> getCoordMap();
}
