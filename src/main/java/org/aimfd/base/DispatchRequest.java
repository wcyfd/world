package org.aimfd.base;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.aimfd.world.player.PlayerManager;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public abstract class DispatchRequest implements ApplicationContextAware {

	protected final Map<String, CallFunc> callFuncMap = new HashMap<>();

	public abstract void dispatch(int clientId, Object msg);

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		init2(applicationContext);
	}

	private void init(ApplicationContext applicationContext) {
		Map<String, PlayerManager> playerManagers = applicationContext.getBeansOfType(PlayerManager.class);
		for (PlayerManager playerManager : playerManagers.values()) {
			Method[] methods = playerManager.getClass().getDeclaredMethods();
			// 搜索所有有ClientRequest标记的方法
			for (Method method : methods) {

				ClientRequest clientRequest = method.getAnnotation(ClientRequest.class);
				if (clientRequest == null) {
					continue;
				}

				CallFunc callFunc = new CallFunc();
				callFunc.setMethod(method);// 放入调用的方法
				callFunc.setName(clientRequest.alias());// 协议名

				callFuncMap.put(clientRequest.alias(), callFunc);
			}
		}
	}

	private void init2(ApplicationContext applicationContext) {
		Map<String, Object> handlers = applicationContext.getBeansWithAnnotation(Handler.class);
		for (Object handler : handlers.values()) {
			Method[] methods = handler.getClass().getDeclaredMethods();
			// 搜索所有有ClientRequest标记的方法
			for (Method method : methods) {

				ClientRequest clientRequest = method.getAnnotation(ClientRequest.class);
				if (clientRequest == null) {
					continue;
				}

				CallFunc callFunc = new CallFunc();
				callFunc.setMethod(method);// 放入调用的方法
				callFunc.setName(clientRequest.alias());// 协议名

				callFuncMap.put(clientRequest.alias(), callFunc);
			}
		}
	}
}
