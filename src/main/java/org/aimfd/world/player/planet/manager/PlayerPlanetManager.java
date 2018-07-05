package org.aimfd.world.player.planet.manager;

import org.aimfd.world.player.PlayerManager;
import org.aimfd.world.player.planet.IPlanetInternal;
import org.aimfd.world.player.planet.IPlanetPublic;
import org.aimfd.world.player.planet.manager.module.PlayerMoveModule;

public class PlayerPlanetManager extends PlayerManager implements IPlanetInternal, IPlanetPublic {

	private PlayerMoveModule playerMoveModule;

	@Override
	public void init() {
		playerMoveModule = new PlayerMoveModule(player);
	}

	@Override
	public void putTerrain(int id, int type) {

	}

	@Override
	public void join() {

	}

	@Override
	public void move(int x, int y) {

	}

}
