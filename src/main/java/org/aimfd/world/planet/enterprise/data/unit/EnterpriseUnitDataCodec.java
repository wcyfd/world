package org.aimfd.world.planet.enterprise.data.unit;

import org.aimfd.world.IDataCodec;

public class EnterpriseUnitDataCodec implements IDataCodec<String, String> {

	protected int id;
	protected int bonus;
	protected int terraformRate;
	protected String account;

	@Override
	public String encode() {
		return null;
	}

	@Override
	public void decode(String input) {

	}

}
