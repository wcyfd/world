package org.aimfd.world.player.planet.manager;

import org.aimfd.world.planet.PlanetManagerRegistry;
import org.aimfd.world.player.PlayerManager;
import org.aimfd.world.player.planet.IPlanetInternal;
import org.aimfd.world.player.planet.IPlanetPublic;

@PlanetManagerRegistry({ IPlanetInternal.class, IPlanetPublic.class })
public class PlayerPlanetManager extends PlayerManager implements IPlanetInternal, IPlanetPublic {

	@Override
	public void join() {

	}

}
