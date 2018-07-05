package org.aimfd.world.handler;

import java.util.Collection;

import org.aimfd.base.ClientRequest;
import org.aimfd.base.ResponseJSONBuilder;
import org.aimfd.base.Route;
import org.aimfd.world.PlayerCache;
import org.aimfd.world.planet.PlanetData;
import org.aimfd.world.player.planet.IPlanetPublic;
import org.aimfd.world.player.role.data.IRoleData;

public class PlanetHandler {
	@ClientRequest(alias = "requestPlanetInfo")
	public static void requestPlanetInfo(int clientId) {
		PlayerCache.getPlayerByClientId(clientId);
	}

	public static void responsePlanetInfo(int clientId, PlanetData planetData) {
		String response = ResponseJSONBuilder.build("responsePlanetInfo", "planetData", planetData);
		Route.send(clientId, response);
	}

	public static void sendMassPlanet(Collection<Integer> clientIds, PlanetData planetData) {
		String response = ResponseJSONBuilder.build("responsePlanetInfo", "planetData", planetData);
		Route.sendMass(clientIds, response);
	}

	@ClientRequest(alias = "requestPutTerrain")
	public static void requestPutTerrain(int clientId, int id, int type) {
		PlayerCache.getPlayerByClientId(clientId).getPlayerManager(IPlanetPublic.class).putTerrain(id, type);
	}

	@ClientRequest(alias = "requestMove")
	public static void requestMove(int clientId, int x, int y) {
		PlayerCache.getPlayerByClientId(clientId).getPlayerManager(IPlanetPublic.class).move(x, y);
	}

	public static void responseMove(int clientId) {
		String response = ResponseJSONBuilder.build("responseMove");
		Route.send(clientId, response);
	}

	public static void sendEnterRole(Collection<Integer> clientIds, IRoleData roleData) {
		String response = ResponseJSONBuilder.build("noticeRoleEnter", "roleData", roleData);
		Route.sendMass(clientIds, response);
	}

	public static void sendLeaveRole(Collection<Integer> clientIds, int roleId) {
		String response = ResponseJSONBuilder.build("noticeRoleLeave", "roleId", roleId);
		Route.sendMass(clientIds, response);
	}

	public static void sendMoveRole(Collection<Integer> clientIds, int roleId) {
		String response = ResponseJSONBuilder.build("noticeRoleMove", "roleId", roleId);
		Route.sendMass(clientIds, response);
	}

}
