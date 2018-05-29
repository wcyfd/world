package org.aimfd.client.socketc;

import org.aimfd.client.handler.AccountRequest;

public class WorldRobot extends Robot {
	public WorldRobot(String ip, int port) {
		super(ip, port, 3);
		registRequest(AccountRequest.class);
	}

}
