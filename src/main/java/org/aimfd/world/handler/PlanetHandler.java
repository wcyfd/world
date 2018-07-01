package org.aimfd.world.handler;

import java.util.Collection;

import org.aimfd.base.ClientRequest;
import org.aimfd.base.ResponseJSONBuilder;
import org.aimfd.base.Route;
import org.aimfd.world.PlayerCache;
import org.aimfd.world.planet.PlanetData;
import org.aimfd.world.player.planet.IPlanetPublic;

public class PlanetHandler {
	@ClientRequest(alias = "requestPlanetInfo")
	public static void requestPlanetInfo(int clientId) {
		PlayerCache.getPlayerByClientId(clientId);
	}

	public static void responsePlanetInfo(int clientId, PlanetData planetData) {
		String response = ResponseJSONBuilder.build("responsePlanetInfo", "planetData", planetData);
		Route.send(clientId, response);
	}

	public static void sendMassPlanet(Collection<Integer> clientIds, PlanetData planetData) {
		String response = ResponseJSONBuilder.build("responsePlanetInfo", "planetData", planetData);
		Route.sendMass(clientIds, response);
	}

	@ClientRequest(alias = "requestPutTerrain")
	public static void requestPutTerrain(int clientId, int id, int type) {
		PlayerCache.getPlayerByClientId(clientId).getPlayerManager(IPlanetPublic.class).putTerrain(id, type);
	}

	@ClientRequest(alias = "requestMove")
	public static void requestMove(int clientId, int x, int y) {

	}

}
