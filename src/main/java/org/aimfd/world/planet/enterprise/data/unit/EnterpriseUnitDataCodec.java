package org.aimfd.world.planet.enterprise.data.unit;

import org.aimfd.world.IDataCodec;

public class EnterpriseUnitDataCodec implements IDataCodec<String, String> {

	protected int id;
	protected String account;
	protected int bonus;
	protected int tr;
	protected int iron;
	protected int ti;

	@Override
	public String encode() {
		return null;
	}

	@Override
	public void decode(String input) {

	}

}
