package org.aimfd.client;

@Deprecated
public class WorldClient extends SocketC {
	public static void main(String[] args) {
		WorldClient client = new WorldClient();
		client.init();
		client.connectServer("127.0.0.1", 8081);

	}
}
