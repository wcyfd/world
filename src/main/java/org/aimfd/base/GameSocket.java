package org.aimfd.base;

import java.util.concurrent.LinkedBlockingQueue;

import org.aimfd.world.PlayerCache;
import org.aimfd.world.player.Player;

import io.netty.channel.Channel;
import io.netty.channel.ChannelPromise;
import io.netty.util.AttributeKey;

public class GameSocket extends SocketCode {
	public static final AttributeKey<ChannelPromise> CHANNEL_PROMISE_KEY = AttributeKey.valueOf("CHANNEL_PROMISE_KEY");
	private final LinkedBlockingQueue<Integer> clientIdQueue = new LinkedBlockingQueue<>();
	protected DispatchRequest dispatchRequest;
	protected int maxConnectionCount;

	public void setDispatchRequest(DispatchRequest dispatchRequest) {
		this.dispatchRequest = dispatchRequest;
	}

	/**
	 * 注册接口
	 * 
	 * @param clazz
	 */
	protected void registHandler(Class<?> clazz) {
		dispatchRequest.registHandler(clazz);
	}

	@Override
	protected void init() {
		// 初始化最高承载的玩家容量

		// 添加可以使用的clientId队列
		for (int i = 0; i < maxConnectionCount; i++) {
			try {
				clientIdQueue.put(i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// 路由初始化
		Route.init(maxConnectionCount);
	}

	@Override
	protected void initData() {

	}

	@Override
	protected void initSpecial() {

	}

	@Override
	public void newConnectIn(Channel channel) {
		Integer topValue = clientIdQueue.peek();
		// 查看栈顶的值有没有可以使用的clientId
		if (topValue == null) {
			channel.close();
			return;
		}

		// 获取空闲的clientId
		Integer clientId = clientIdQueue.poll();
		// 获取对应的玩家
		Player player = PlayerCache.getPlayerByClientId(clientId);
		// 通道与编号绑定
		channel.attr(CLIENT_ID_KEY).set(clientId);

		player.initPlayer(channel);

	}

	@Override
	protected void serverDataReceive(Channel channel, String msg) {
		Integer clientId = channel.attr(CLIENT_ID_KEY).get();

		dispatchRequest.dispatch(clientId, msg);
	}

	@Override
	public void myServerDisconnect(Channel channel, String serverName) {
		Integer clientID = channel.attr(CLIENT_ID_KEY).get();
		disconnect(clientID);
	}

	@Override
	public void myServerDisconnect(Integer clientID, String serverName) {
		disconnect(clientID);
	}

	private void disconnect(Integer clientID) {
		// 空则返回
		if (clientID == null)
			return;
		// 超出范围则返回
		if (clientID < 0 || clientID >= maxConnectionCount)
			return;

		Player player = PlayerCache.getPlayerByClientId(clientID);

		player.getLogger().info("玩家下线");
		player.offline();// 玩家下线

		Channel channel = Route.getChannel(clientID);

		ChannelPromise promise = channel.attr(CHANNEL_PROMISE_KEY).get();
		if (promise != null) {
			promise.setSuccess();
		}

		player.getLogger().info("玩家数据保存");
		player.dbSave();// 玩家数据保存
		player.getLogger().info("玩家数据开始清空");
		player.resetData();// 玩家数据清空

		clientIdQueue.add(clientID);// id归还
	}

}
