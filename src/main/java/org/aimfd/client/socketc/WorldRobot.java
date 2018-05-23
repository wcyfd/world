package org.aimfd.client.socketc;

import org.aimfd.client.handler.AccountRequest;

public class WorldRobot extends Robot {
	public WorldRobot() {
		registRequest(AccountRequest.class);
	}
}
