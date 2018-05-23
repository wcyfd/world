package org.aimfd.client.socketc;

import org.aimfd.client.handler.AccountRequest;

public class ClientMain {

	public static void main(String[] args) {
		WorldRobot robot = new WorldRobot();
		robot.start();

		robot.getRequest(AccountRequest.class).login("wcy", "jor", 25);
	}
}
