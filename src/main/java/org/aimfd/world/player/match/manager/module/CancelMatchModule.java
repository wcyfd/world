package org.aimfd.world.player.match.manager.module;

import org.aimfd.world.player.Player;
import org.aimfd.world.system.SystemICenter;
import org.aimfd.world.system.match.ISystemMatchPublic;

public class CancelMatchModule {
	private ISystemMatchPublic systemMatchPublic;
	private Player player;

	public CancelMatchModule(Player player) {
		this.player = player;
		this.systemMatchPublic = SystemICenter.getSystemInterface(ISystemMatchPublic.class);
	}

	public void cancel() {
		systemMatchPublic.cancelMatch(player.getAccount());
	}
}
