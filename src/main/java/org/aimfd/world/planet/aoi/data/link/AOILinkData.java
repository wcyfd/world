package org.aimfd.world.planet.aoi.data.link;

import java.util.HashMap;
import java.util.Map;

import org.aimfd.base.IData;
import org.aimfd.world.planet.aoi.data.link.unit.TenNodeWrapper;
import org.aimfd.world.player.role.data.unit.IRoleCoordUnitData;

/**
 * 十字链表法AOI
 * 
 * @author AIM
 *
 */
public class AOILinkData extends AOILinkDataCodec implements IData, IAOILinkData {

	public AOILinkData() {
		listX = new TenNodeWrapper<>();
		listY = new TenNodeWrapper<>();
		map = new HashMap<>();
	}

	@Override
	public TenNodeWrapper<IRoleCoordUnitData> getLinkedListX() {
		return listX;
	}

	@Override
	public TenNodeWrapper<IRoleCoordUnitData> getLinkedListY() {
		return listY;
	}

	@Override
	public Map<Integer, TenNodeWrapper<IRoleCoordUnitData>> getCoordMap() {
		return map;
	}

	@Override
	public void resetData() {
		listX.setValue(null);// 清空当前的前后连接
		listY.setValue(null);
		// 删除所有其他节点的前后连接
		for (TenNodeWrapper<IRoleCoordUnitData> nodeWrapper : map.values()) {
			nodeWrapper.getNodeX().removeNext();
			nodeWrapper.getNodeX().removePrev();

			nodeWrapper.getNodeY().removeNext();
			nodeWrapper.getNodeY().removePrev();

			nodeWrapper.setValue(null);
		}
		map.clear();
	}
}
