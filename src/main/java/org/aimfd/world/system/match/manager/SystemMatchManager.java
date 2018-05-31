package org.aimfd.world.system.match.manager;

import org.aimfd.world.system.SystemManager;
import org.aimfd.world.system.match.IMatchPublic;
import org.aimfd.world.system.match.manager.module.BeginMatchModule;
import org.aimfd.world.system.match.manager.module.CancelMatchModule;

public class SystemMatchManager extends SystemManager implements IMatchPublic {

	private BeginMatchModule beginMatchModule;
	private CancelMatchModule cancelMatchModule;

	@Override
	protected void init() {
		beginMatchModule = new BeginMatchModule();
		cancelMatchModule = new CancelMatchModule();
	}

	@Override
	public void beginMatch(String account) {
		beginMatchModule.beginMatch(account);
	}

	@Override
	public void cancelMatch(String account) {
		cancelMatchModule.cancelMatch(account);
	}

}
