package org.aimfd.client;

import org.aimfd.client.handler.AccountRequest;
import org.aimfd.client.socketc.WorldRobot;

public class ClientMain {

	public static void main(String[] args) {
		WorldRobot robot = new WorldRobot("localhost", 8081);
		robot.login();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		robot.getRequest(AccountRequest.class).login("wcy", "jor", 12);
	}
}
