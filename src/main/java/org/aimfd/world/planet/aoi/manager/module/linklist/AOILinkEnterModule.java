package org.aimfd.world.planet.aoi.manager.module.linklist;

import org.aimfd.world.planet.Planet;
import org.aimfd.world.planet.aoi.data.link.IAOILinkData;
import org.aimfd.world.planet.aoi.data.link.unit.NodeWrapper;
import org.aimfd.world.planet.aoi.data.link.unit.TenNodeWrapper;
import org.aimfd.world.player.role.data.IRoleData;
import org.aimfd.world.player.role.data.unit.IRoleCoordUnitData;

public class AOILinkEnterModule {
	private IAOILinkData linkData;

	public AOILinkEnterModule(Planet planet) {

	}

	public void enter(IRoleData roleData, int x, int y) {
		int roleId = roleData.getRoleId();
		IRoleCoordUnitData coordData = roleData.getCoord();

		TenNodeWrapper<IRoleCoordUnitData> node = linkData.getCoordMap().get(roleId);
		if (node == null) {
			node = new TenNodeWrapper<>();
			linkData.getCoordMap().put(roleId, node);
		}

		node.setValue(coordData);

		sortInsert(linkData.getLinkedListX().getNodeX(), node);
	}

	private void sortInsert(NodeWrapper<IRoleCoordUnitData> root, TenNodeWrapper<IRoleCoordUnitData> node) {
		NodeWrapper<IRoleCoordUnitData> current = root.getNextNode();
		IRoleCoordUnitData coordData = node.getValue();

		if (current == null) {
			root.addNext(node);
		} else {
			while (true) {
				// 从小到大
				if (coordData.getX() < current.getValue().getX()) {
					current.addPrev(node);
					break;
				} else {
					if (current.getNextNode() == null) {
						current.addNext(node);
						break;
					}

					current = current.getNextNode();
				}
			}

		}
	}
}
