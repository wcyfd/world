package org.aimfd.base;

import io.netty.channel.Channel;

public class SocketCode extends Socket<String> {

	/**
	 * 服务器建立
	 */
	public void buildServer() {
		init();
		initData();
		initSpecial();
	}

	protected void init() {

	}

	protected void initData() {

	}

	protected void initSpecial() {

	}

	@Override
	protected void serverDataReceive(Channel channel, String msg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void newConnectIn(Channel channel) {
		// TODO Auto-generated method stub

	}

	@Override
	public void myServerDisconnect(Channel channel, String serverName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void myServerDisconnect(Integer clientID, String serverName) {
		// TODO Auto-generated method stub

	}

}
