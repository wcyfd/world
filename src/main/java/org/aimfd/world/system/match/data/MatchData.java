package org.aimfd.world.system.match.data;

import java.util.HashSet;
import java.util.Set;

public class MatchData extends MatchDataCodec implements IMatchData {

	public MatchData() {
		accountSet = new HashSet<>();
	}

	@Override
	public Set<String> getAccountSet() {
		return accountSet;
	}

}
