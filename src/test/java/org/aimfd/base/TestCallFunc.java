package org.aimfd.base;

import java.lang.reflect.Method;

import org.aimfd.world.PlayerCache;
import org.aimfd.world.handler.AccountHandler;
import org.aimfd.world.player.Player;
import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class TestCallFunc {
	private DispatchRequest request;
	private Player player;

	@Before
	public void testCallFuncBefore() {
		request = new DispatchJSONRequest();
		Method method = null;
		try {
			method = AccountHandler.class.getDeclaredMethod("login", int.class, String.class, String.class, int.class);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		CallFunc callFunc = new CallFunc();
		callFunc.setMethod(method);
		callFunc.setName("login");
		request.callFuncMap.put("login", callFunc);

		player = new Player(1);
		PlayerCache.addPlayer(player);
	}

	@Test
	public void testCallFunc() {
		JSONObject requestJSON = new JSONObject();
		requestJSON.put("name", "login");
		JSONObject param = new JSONObject();
		param.put("account", "wcy");
		param.put("name", "wcyName");
		param.put("age", 25);
		requestJSON.put("param", param);
		System.out.println(requestJSON);
		long t1 = System.currentTimeMillis();
		long t2 = System.currentTimeMillis();
		request.dispatch(1, requestJSON.toJSONString());
		System.out.println(t2 - t1);
	}
}
