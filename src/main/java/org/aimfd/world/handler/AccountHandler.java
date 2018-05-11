package org.aimfd.world.handler;

import org.aimfd.base.ClientParam;
import org.aimfd.base.ClientRequest;
import org.aimfd.base.Handler;
import org.aimfd.world.PlayerCache;
import org.aimfd.world.player.account.IAccountPublic;
import org.springframework.stereotype.Service;

@Service
@Handler
public class AccountHandler {
	@ClientRequest(alias = "login")
	public static void login(int clientId, @ClientParam("account") String account, @ClientParam("name") String name, @ClientParam("age") int age) {
		PlayerCache.getPlayerByClientId(clientId).getPlayerManager(IAccountPublic.class).login(account, name, age);
	}
}
