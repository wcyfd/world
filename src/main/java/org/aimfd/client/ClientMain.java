package org.aimfd.client;

import org.aimfd.client.handler.AccountRequest;
import org.aimfd.client.handler.MatchRequest;
import org.aimfd.client.socketc.WorldRobot;

public class ClientMain {

	public static void main(String[] args) {
		WorldRobot robot = new WorldRobot("localhost", 8081);
		robot.login();

		sleep(4000);

		robot.getRequest(AccountRequest.class).login("wcy", "jor", 12);

		sleep(2000);
		robot.getRequest(MatchRequest.class).beginMatch();

	}

	private static void sleep(long m) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
