package org.aimfd.world.system.match.data;

import java.util.LinkedList;

public class MatchData extends MatchDataCodec implements IMatchData {

	public MatchData() {
		accountQueue = new LinkedList<>();
	}

	@Override
	public boolean contains(String account) {
		return accountQueue.contains(account);
	}

	@Override
	public void addAccount(String account) {
		accountQueue.add(account);
	}

	@Override
	public void removeAccount(String account) {
		accountQueue.remove(account);
	}

	@Override
	public int getQueueLength() {
		return accountQueue.size();
	}

	@Override
	public String poll() {
		return accountQueue.poll();
	}

}
