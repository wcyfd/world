package org.aimfd.client.handler;

import org.aimfd.client.ClientMain;

import com.alibaba.fastjson.JSONObject;

public class AccountRequest {
	public static void login(String account, String name, int age) {
		JSONObject req = new JSONObject();
		req.put("name", "login");

		JSONObject param = new JSONObject();
		param.put("account", account);
		param.put("name", name);
		param.put("age", age);

		req.put("param", param);

		System.out.println(req);
		ClientMain.client.getChannel().writeAndFlush(req.toJSONString());
	}
}
