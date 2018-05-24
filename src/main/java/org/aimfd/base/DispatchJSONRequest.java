package org.aimfd.base;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Component
public class DispatchJSONRequest extends DispatchRequest {

	@Override
	public void dispatch(int clientId, Object msg) {

		JSONObject jsonObject = JSON.parseObject((String) msg);
		String alias = jsonObject.getString("name");

		// 获取回调方法
		CallFunc callFunc = callFuncMap.get(alias);
		if (callFunc == null) {
			return;
		}
		Method method = callFunc.getMethod();

		Parameter[] parameters = callFunc.getMethod().getParameters();
		if (parameters.length == 1) {// 只有一个参数，则直接调用
			try {
				method.invoke(null, clientId);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		} else {
			// 获得输入的参数的值的数组
			Object[] objects = getMethodInputParams(jsonObject, clientId, parameters);

			try {
				method.invoke(null, objects);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 获得方法的入参值
	 * 
	 * @param jsonObject
	 * @param parameters
	 * @return
	 */
	private Object[] getMethodInputParams(JSONObject jsonObject, int clientId, Parameter[] parameters) {

		JSONObject requestParamObject = jsonObject.getJSONObject("param");

		// 声明要输入的参数的数组
		Object[] objects = new Object[parameters.length];
		objects[0] = clientId;
		for (int i = 1; i < parameters.length; i++) {
			Parameter parameter = parameters[i];
			ClientParam clientParam = parameter.getAnnotation(ClientParam.class);

			// 获得请求对应方法参数的值
			String name = clientParam.value();
			// 获得请求的参数的值
//			Object value = requestParamObject.get(name);
			Object value = parseValue(requestParamObject, parameter, name);

			// 如果是列表参数
			if (value instanceof JSONArray) {
				Class<?> clazz = parameter.getType();
				Type type = ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[0];
				try {
					value = requestParamObject.getJSONArray(name).toJavaList(Class.forName(type.getTypeName()));
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}

			objects[i] = value;
		}
		return objects;
	}

	private Object parseValue(JSONObject requestParamObject, Parameter parameter, String name) {
		Object value = requestParamObject.get(name);

		if (value instanceof JSONArray) {
			Class<?> clazz = parameter.getType();
			Type type = ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[0];
			try {
				value = requestParamObject.getJSONArray(name).toJavaList(Class.forName(type.getTypeName()));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else if (value instanceof JSONObject) {
			Class<?> clazz = parameter.getType();
			value = requestParamObject.getObject(name, clazz);
		}

		return value;
	}
}
