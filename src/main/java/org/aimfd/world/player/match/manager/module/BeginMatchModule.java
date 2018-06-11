package org.aimfd.world.player.match.manager.module;

import org.aimfd.world.handler.MatchHandler;
import org.aimfd.world.player.Player;
import org.aimfd.world.system.SystemICenter;
import org.aimfd.world.system.match.ISystemMatchPublic;

public class BeginMatchModule {
	private ISystemMatchPublic systemMatchPublic;
	private Player player;

	public BeginMatchModule(Player player) {
		this.player = player;
		this.systemMatchPublic = SystemICenter.getSystemInterface(ISystemMatchPublic.class);
	}

	public void match() {
		MatchHandler.responseMatch(player.getClientId());
		
		systemMatchPublic.beginMatch(player.getAccount());
	}
}
