package org.aimfd.base;

import java.util.Collection;

import org.aimfd.world.PlayerCache;
import org.slf4j.Logger;

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

	/**
	 * 发送给一个人
	 * 
	 * @param clientId
	 * @param obj
	 */
	public static void send(int clientId, Object obj) {
		Channel channel = channels[clientId];
		if (channel == null || !channel.isActive()) {
			return;
		}

		Logger logger = PlayerCache.getPlayerByClientId(clientId).getLogger();

		logger.info("{}", obj);
		channel.writeAndFlush(obj);
	}

	/**
	 * 群发消息
	 */
	public static void sendMass(Collection<Integer> clientIds, Object obj) {
		for (int clientId : clientIds) {
			send(clientId, obj);
		}
	}

	public static Channel getChannel(int clientId) {
		return channels[clientId];
	}

}
