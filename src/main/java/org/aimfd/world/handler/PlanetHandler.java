package org.aimfd.world.handler;

import java.util.Collection;

import org.aimfd.base.ClientRequest;
import org.aimfd.base.ResponseJSONBuilder;
import org.aimfd.base.Route;
import org.aimfd.world.PlayerCache;
import org.aimfd.world.planet.PlanetData;

public class PlanetHandler {
	@ClientRequest(alias = "getPlanetInfo")
	public static void getPlanetInfo(int clientId) {
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
}
