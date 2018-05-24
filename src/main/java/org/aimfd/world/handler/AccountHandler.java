package org.aimfd.world.handler;

import org.aimfd.base.ClientParam;
import org.aimfd.base.ClientRequest;
import org.aimfd.base.Handler;
import org.aimfd.base.ResponseJSONBuilder;
import org.aimfd.base.Route;
import org.aimfd.world.PlayerCache;
import org.aimfd.world.player.PeaceData;
import org.aimfd.world.player.account.IAccountPublic;
import org.springframework.stereotype.Service;

@Service
@Handler
public class AccountHandler {
	@ClientRequest(alias = "requestLogin")
	public static void requestLogin(int clientId, @ClientParam("account") String account, @ClientParam("name") String name, @ClientParam("age") int age) {
		PlayerCache.getPlayerByClientId(clientId).getPlayerManager(IAccountPublic.class).login(account, name, age);
	}

	public static void responseLogin(int clientId, PeaceData peaceData) {
		String response = ResponseJSONBuilder.build("loginResponse", "peaceData", peaceData);
		Route.send(clientId, response);
	}

}
