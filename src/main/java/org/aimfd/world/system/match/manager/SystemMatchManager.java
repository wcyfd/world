package org.aimfd.world.system.match.manager;

import org.aimfd.base.SystemManagerRegistry;
import org.aimfd.world.system.SystemManager;
import org.aimfd.world.system.match.IMatchPublic;
import org.springframework.stereotype.Service;

@Service
@SystemManagerRegistry(IMatchPublic.class)
public class SystemMatchManager extends SystemManager implements IMatchPublic {

	@Override
	public void beginMatch(String account) {
		// TODO Auto-generated method stub

	}

	@Override
	public void cancelMatch(String account) {
		// TODO Auto-generated method stub

	}

}
