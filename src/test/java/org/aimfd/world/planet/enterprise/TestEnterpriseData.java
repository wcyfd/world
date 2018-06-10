package org.aimfd.world.planet.enterprise;

import org.aimfd.world.planet.Planet;
import org.aimfd.world.planet.enterprise.data.EnterpriseData;
import org.aimfd.world.planet.enterprise.data.IEnterpriseData;
import org.aimfd.world.planet.enterprise.manager.module.EnterpriseInitModule;
import org.assertj.core.util.Lists;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

public class TestEnterpriseData {
	@Test
	public void codec() {
		EnterpriseData data = new EnterpriseData();

		JSONObject json = data.encode();
		System.out.println(json);

		EnterpriseData decode = new EnterpriseData();
		decode.decode(json);
	}

	@Test
	public void testEnterpriseInitModule() {
		Planet planet = new Planet(0);
		EnterpriseInitModule module = new EnterpriseInitModule(planet);

		module.applyEnterprises(Lists.newArrayList("wcy", "yn", "wcd"));
		IEnterpriseData idata = planet.getPlanetAllData().getPlanetData().getEnterpriseData();
		EnterpriseData data = (EnterpriseData) idata;
		JSONObject json = data.encode();
		System.out.println(json);
	}
}
