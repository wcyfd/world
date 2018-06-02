package org.aimfd.base;

import java.util.concurrent.LinkedBlockingQueue;

import org.aimfd.world.PlayerCache;
import org.aimfd.world.player.Player;

import io.netty.channel.Channel;

public class GameSocket extends SocketCode {
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

	}

	@Override
	public void myServerDisconnect(Integer clientID, String serverName) {
		Player player = PlayerCache.getPlayerByClientId(clientID);

		player.dbSave();// 玩家数据保存
		player.resetData();// 数据清空

		clientIdQueue.add(clientID);// id归还
	}

}
