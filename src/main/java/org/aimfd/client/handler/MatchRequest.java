package org.aimfd.client.handler;

import org.aimfd.client.Request;
import org.aimfd.client.socketc.Robot;

import com.alibaba.fastjson.JSONObject;

public class MatchRequest extends Request {

	public MatchRequest(Robot robot) {
		super(robot);
	}

	public void beginMatch() {
		JSONObject req = new JSONObject();
		req.put("name", "requestMatchBegin");

		JSONObject param = new JSONObject();

		req.put("param", param);

		robot.send(req);
	}

	public void cancelMatch() {
		JSONObject req = new JSONObject();
		req.put("name", "requestMatchCancel");

		JSONObject param = new JSONObject();

		req.put("param", param);

		robot.send(req);
	}

}
