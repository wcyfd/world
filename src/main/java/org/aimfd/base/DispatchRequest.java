package org.aimfd.base;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public abstract class DispatchRequest {

	protected final Map<String, CallFunc> callFuncMap = new HashMap<>();

	/**
	 * 分发消息
	 * 
	 * @param clientId
	 * @param msg
	 */
	public abstract void dispatch(int clientId, Object msg);

	/**
	 * 注册handler
	 * 
	 * @param handlerClass
	 */
	public void registHandler(Class<?> handlerClass) {
		Method[] methods = handlerClass.getDeclaredMethods();
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
