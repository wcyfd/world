package org.aimfd.world.player.planet.manager.module;

import org.aimfd.world.planet.PlanetICenter;
import org.aimfd.world.planet.enterprise.IEnterpriseInternal;
import org.aimfd.world.planet.terrain.ITerrainPublic;
import org.aimfd.world.player.Player;
import org.aimfd.world.system.SystemICenter;
import org.aimfd.world.system.room.ISystemRoomPublic;

public class PlayerPutTerrainModule {
	private Player player;
	private ISystemRoomPublic systemRoomPublic;

	public PlayerPutTerrainModule(Player player) {
		this.player = player;
		this.systemRoomPublic = SystemICenter.getSystemInterface(ISystemRoomPublic.class);
	}

	public void putTerrain(int id, int type) {
		int planetId = systemRoomPublic.getPlanetId(player.getAccount());
		if (planetId == -1) {
			return;
		}

		ITerrainPublic terrainPublic = PlanetICenter.getInterface(planetId, ITerrainPublic.class);
		IEnterpriseInternal enterpriseInternal = PlanetICenter.getInterface(planetId, IEnterpriseInternal.class);
		int enterpriseId = enterpriseInternal.getEnterpriseId(player.getAccount());

		terrainPublic.putTerrain(id, enterpriseId, type);
	}
}
