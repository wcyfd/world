package org.aimfd.client.socketc;

import org.aimfd.client.handler.AccountRequest;
import org.aimfd.client.handler.MatchRequest;

public class WorldRobot extends Robot {
	public WorldRobot(String ip, int port) {
		super(ip, port, 0);
		registRequest(AccountRequest.class);
		registRequest(MatchRequest.class);
	}

}
