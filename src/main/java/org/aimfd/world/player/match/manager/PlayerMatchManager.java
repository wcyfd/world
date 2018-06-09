package org.aimfd.world.player.match.manager;

import org.aimfd.world.player.PlayerManager;
import org.aimfd.world.player.match.IMatchPublic;
import org.aimfd.world.player.match.manager.module.BeginMatchModule;
import org.aimfd.world.player.match.manager.module.CancelMatchModule;

public class PlayerMatchManager extends PlayerManager implements IMatchPublic {

	private BeginMatchModule beginMatchModule;
	private CancelMatchModule cancelMatchModule;

	@Override
	public void init() {
		beginMatchModule = new BeginMatchModule(player);
		cancelMatchModule = new CancelMatchModule(player);
	}

	@Override
	public void match() {
		beginMatchModule.match();
	}

	@Override
	public void cancel() {
		cancelMatchModule.cancel();
	}

}
