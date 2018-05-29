package org.aimfd.client.handler;

import org.aimfd.client.Request;
import org.aimfd.client.socketc.Robot;

import com.alibaba.fastjson.JSONObject;

public class AccountRequest extends Request {

	public AccountRequest(Robot client) {
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

		robot.send(req);
	}
}
