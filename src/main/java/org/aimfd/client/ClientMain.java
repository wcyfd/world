package org.aimfd.client;

import org.aimfd.client.handler.AccountRequest;

public class ClientMain {
	public final static Client client = new Client();

	public static void main(String[] args) {
		client.start();

		AccountRequest.login("wcy", "jor", 25);
	}
}
