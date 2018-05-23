package org.aimfd.client.socketc;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class Robot extends Client {

	protected Map<Class<?>, Object> requestMap = new HashMap<>();

	protected void registRequest(Class<?> requestClazz) {
		if (requestMap.containsKey(requestClazz)) {
			return;
		}

		try {
			Constructor<?> construct = requestClazz.getConstructor(Client.class);
			Object request = construct.newInstance(this);
			requestMap.put(requestClazz, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T getRequest(Class<T> clazz) {
		return (T) requestMap.get(clazz);
	}
}