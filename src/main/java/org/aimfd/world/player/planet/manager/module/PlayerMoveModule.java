package org.aimfd.world.player.planet.manager.module;

import org.aimfd.world.player.Player;
import org.aimfd.world.player.role.data.IRoleData;

public class PlayerMoveModule {
	private IRoleData roleData;
	public PlayerMoveModule(Player player) {
		roleData = player.getPlayerAllData().getPeaceData().getRoleData();
	}
	
	public void move(int x,int y) {
		
	}
}
