package org.aimfd.base;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class TestResponseJSONBuilder {
	@Test
	public void testResponseJSONBuilder() {
		Set<Integer> set = new HashSet<>();
		set.add(3);
		set.add(32);
		set.add(34);
		set.add(3111);
		String json = ResponseJSONBuilder.build("loginResponse", "peaceData", set);
		System.out.println(json);

		JSONObject obj = JSONObject.parseObject(json);
		JSONObject param = obj.getJSONObject("param");
		param.getJSONArray("peaceData");
	}
}
