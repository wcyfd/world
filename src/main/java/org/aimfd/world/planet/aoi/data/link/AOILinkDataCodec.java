package org.aimfd.world.planet.aoi.data.link;

import java.util.Map;

import org.aimfd.base.IDataJSONCodec;
import org.aimfd.world.planet.aoi.data.link.unit.TenNodeWrapper;
import org.aimfd.world.player.role.data.unit.IRoleCoordUnitData;

import com.alibaba.fastjson.JSONObject;

public class AOILinkDataCodec implements IDataJSONCodec {
	protected TenNodeWrapper<IRoleCoordUnitData> listX;
	protected TenNodeWrapper<IRoleCoordUnitData> listY;
	protected Map<Integer, TenNodeWrapper<IRoleCoordUnitData>> map;

	@Override
	public JSONObject encode() {
		JSONObject json = new JSONObject();
		
		return json;
	}

	@Override
	public void decode(JSONObject source) {
	}

}
