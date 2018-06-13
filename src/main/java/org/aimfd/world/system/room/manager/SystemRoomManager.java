package org.aimfd.world.system.room.manager;

import org.aimfd.world.system.SystemManager;
import org.aimfd.world.system.room.ISystemRoomPublic;
import org.aimfd.world.system.room.manager.module.PlanetLocationModule;
import org.aimfd.world.system.room.manager.module.RegistModule;
import org.aimfd.world.system.room.manager.module.UnRegistModule;

/**
 * 房间管理器
 * 
 * @author AIM
 *
 */
public class SystemRoomManager extends SystemManager implements ISystemRoomPublic {

	private RegistModule registModule;
	private UnRegistModule unRegistModule;
	private PlanetLocationModule planetLocationModule;

	@Override
	protected void init() {
		registModule = new RegistModule();
		unRegistModule = new UnRegistModule();
		planetLocationModule = new PlanetLocationModule();
	}

	@Override
	public void regist(String account, int planetId) {
		registModule.regist(account, planetId);
	}

	@Override
	public void unregist(String account) {
		unRegistModule.unRegist(account);
	}

	@Override
	public boolean atRoom(String account) {
		return registModule.atRoom(account);
	}

	@Override
	public int getPlanetId(String account) {
		return planetLocationModule.getPlanetId(account);
	}

}
