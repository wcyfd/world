package org.aimfd.world.planet;

import org.aimfd.base.IDataJSONCodec;
import org.aimfd.world.planet.aoi.data.IAOIGridData;
import org.aimfd.world.planet.enterprise.data.EnterpriseData;
import org.aimfd.world.planet.environment.data.EnvironmentData;
import org.aimfd.world.planet.roles.data.IRolesData;
import org.aimfd.world.planet.terrain.data.TerrainData;

import com.alibaba.fastjson.JSONObject;

public class PlanetDataCodec implements IDataJSONCodec {

	protected EnvironmentData environmentData;
	protected EnterpriseData enterpriseData;
	protected TerrainData terrainData;
	protected IRolesData roleData;
	protected IAOIGridData aoiData;

	@Override
	public JSONObject encode() {
		JSONObject json = new JSONObject();
		json.put("environmentData", environmentData.encode());
		json.put("enterpriseData", enterpriseData.encode());
		// json.put("terrainData", terrainData.encode());
		json.put("roleData", ((IDataJSONCodec) roleData).encode());
		json.put("aoiData", ((IDataJSONCodec) aoiData).encode());
		return json;
	}

	@Override
	public void decode(JSONObject source) {
//		environmentData.resetData();
//		enterpriseData.resetData();
//		
//		// terrainData.resetData();
//
//		environmentData.decode(source.getJSONObject("environmentData"));
//		enterpriseData.decode(source.getJSONObject("enterpriseData"));
		
		// source.getJSONObject("terrainData");
		//不允许客户端序列化
	}

}
