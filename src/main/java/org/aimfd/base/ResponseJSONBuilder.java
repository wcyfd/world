package org.aimfd.base;

import com.alibaba.fastjson.JSONObject;

public class ResponseJSONBuilder {
	/**
	 * 整合响应包
	 * 
	 * @param responseName
	 * @param params
	 *            传入的参数必须是一个参数名（String），一个参数值(Object)
	 */
	public static String build(String responseName, Object... params) {
		JSONObject json = new JSONObject();
		json.put("name", responseName);

		JSONObject paramJson = new JSONObject();
		json.put("param", paramJson);

		if (params == null) {
			params = new Object[] {};
		}

		for (int i = 0; i < params.length; i += 2) {
			String paramName = (String) params[i];
			Object paramObject = params[i + 1];
			paramJson.put(paramName, paramObject);
		}

		return json.toJSONString();
	}
}
