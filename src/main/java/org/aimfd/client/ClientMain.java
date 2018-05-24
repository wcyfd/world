package org.aimfd.client;

import org.aimfd.client.handler.AccountRequest;
import org.aimfd.client.socketc.WorldRobot;

public class ClientMain {

	public static void main(String[] args) {
		WorldRobot robot = new WorldRobot();
		robot.start();

		robot.getRequest(AccountRequest.class).login("wcy", "jor", 25);
	}
}
