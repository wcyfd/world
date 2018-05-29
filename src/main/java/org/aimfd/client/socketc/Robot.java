package org.aimfd.client.socketc;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.alibaba.fastjson.JSONObject;

import io.netty.channel.ChannelHandlerContext;

public class Robot extends Client {

	protected Map<Class<?>, Object> requestMap = new HashMap<>();
	protected Map<Class<?>, Object> responseMap = new HashMap<>();
	protected Map<Class<?>, Object> noticeMap = new HashMap<>();

	protected ExecutorService logicThread;

	public Robot(String ip, int port, int reconnectCount) {
		super(ip, port, reconnectCount);
		logicThread = Executors.newSingleThreadExecutor();
	}

	protected void registRequest(Class<?>... clazzes) {

		for (Class<?> clazz : clazzes) {
			if (requestMap.containsKey(clazz)) {
				return;
			}

			try {
				Constructor<?> construct = clazz.getConstructor(Robot.class);
				Object request = construct.newInstance(this);
				requestMap.put(clazz, request);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	protected void registResponse(Class<?>... clazzes) {

		for (Class<?> clazz : clazzes) {
			if (responseMap.containsKey(clazz)) {
				return;
			}

			try {
				Constructor<?> construct = clazz.getConstructor(Robot.class);
				Object request = construct.newInstance(this);
				responseMap.put(clazz, request);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	protected void registNotice(Class<?>... clazzes) {

		for (Class<?> clazz : clazzes) {
			if (noticeMap.containsKey(clazz)) {
				return;
			}

			try {
				Constructor<?> construct = clazz.getConstructor(Robot.class);
				Object request = construct.newInstance(this);
				noticeMap.put(clazz, request);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	@SuppressWarnings("unchecked")
	public <T> T getRequest(Class<T> clazz) {
		return (T) requestMap.get(clazz);
	}

	@Override
	protected void receiveData(ChannelHandlerContext ctx, String arg1) throws Exception {
		JSONObject json = JSONObject.parseObject(arg1);
		logicThread.execute(new Runnable() {

			@Override
			public void run() {

			}

		});
	}

	public void login() {
		connect();
	}

	public void logout() {
		stopClient();
	}

	public void send(Object msg) {
		JSONObject json = (JSONObject) msg;
		this.getChannel().writeAndFlush(json.toJSONString());
	}

	@Override
	protected void reconnectFailed() {
		System.exit(0);
	}

}