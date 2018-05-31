package org.aimfd.world.system.room.manager;

import org.aimfd.world.system.SystemManager;
import org.aimfd.world.system.room.IRoomPublic;
import org.aimfd.world.system.room.manager.module.RegistModule;
import org.aimfd.world.system.room.manager.module.UnRegistModule;

public class SystemRoomManager extends SystemManager implements IRoomPublic {

	private RegistModule registModule;
	private UnRegistModule unRegistModule;

	@Override
	protected void init() {
		registModule = new RegistModule();
		unRegistModule = new UnRegistModule();
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

}
