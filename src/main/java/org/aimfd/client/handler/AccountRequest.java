package org.aimfd.client.handler;

import org.aimfd.client.Request;
import org.aimfd.client.socketc.Client;

import com.alibaba.fastjson.JSONObject;

public class AccountRequest extends Request {

	public AccountRequest(Client client) {
		super(client);
	}

	public void login(String account, String name, int age) {
		JSONObject req = new JSONObject();
		req.put("name", "requestLogin");

		JSONObject param = new JSONObject();
		param.put("account", account);
		param.put("name", name);
		param.put("age", age);

		req.put("param", param);

		client.getChannel().writeAndFlush(req.toJSONString());
	}
}
