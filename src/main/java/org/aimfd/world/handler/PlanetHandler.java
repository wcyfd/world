package org.aimfd.world.handler;

import org.aimfd.base.ClientRequest;
import org.aimfd.base.Route;
import org.aimfd.world.PlayerCache;
import org.aimfd.world.planet.PlanetData;

import com.alibaba.fastjson.JSONObject;

public class PlanetHandler {
	@ClientRequest(alias = "getPlanetInfo")
	public static void getPlanetInfo(int clientId) {
		PlayerCache.getPlayerByClientId(clientId);
	}

	public static void sendPlanetInfo(int clientId, PlanetData planetData) {
		JSONObject jsonObject = new JSONObject();

		Route.send(clientId, planetData);
	}
}
