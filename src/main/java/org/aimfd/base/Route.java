package org.aimfd.base;

import io.netty.channel.Channel;

public class Route {
	private static Channel[] channels;

	public static void init(int maxCount) {
		channels = new Channel[maxCount];
	}

	public static void initChannel(int clientId, Channel channel) {
		channels[clientId] = channel;
	}

	public static void resetChannel(int clientId) {
		channels[clientId] = null;
	}

	public static void send(int clientId, Object obj) {
		Channel channel = channels[clientId];
		if (channel == null || !channel.isActive()) {
			return;
		}

		System.out.println(obj);
		channel.writeAndFlush(obj);

	}

}
