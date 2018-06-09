package org.aimfd.world.handler;

import org.aimfd.base.ClientRequest;
import org.aimfd.base.ResponseJSONBuilder;
import org.aimfd.base.Route;
import org.aimfd.world.PlayerCache;
import org.aimfd.world.player.match.IMatchPublic;

public class MatchHandler {
	@ClientRequest(alias = "requestMatchBegin")
	public static void requestMatchBegin(int clientId) {
		PlayerCache.getPlayerByClientId(clientId).getPlayerManager(IMatchPublic.class).match();
	}

	public static void responseMatch(int clientId) {
		String response = ResponseJSONBuilder.build("responseMatchBegin");
		Route.send(clientId, response);
	}

	@ClientRequest(alias = "requestMatchCancel")
	public static void cancelMatch(int clientId) {
		PlayerCache.getPlayerByClientId(clientId).getPlayerManager(IMatchPublic.class).cancel();
	}

	public static void responseCancel(int clientId) {
		String response = ResponseJSONBuilder.build("responseMatchCancel");
		Route.send(clientId, response);
	}

}
