package org.aimfd.world.planet.enterprise.data.unit;

import org.aimfd.world.IData;

/**
 * 企业
 * 
 * @author AIM
 *
 */
public class EnterpriseUnitData extends EnterpriseUnitDataCodec implements IData, IEnterpriseUnitData {

	public EnterpriseUnitData(int id) {
		this.id = id;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public String getAccount() {
		return account;
	}

	@Override
	public void setAccount(String account) {
		this.account = account;
	}

	@Override
	public int getTerraformRate() {
		return terraformRate;
	}

	@Override
	public void setTerraformRate(int rate) {
		this.terraformRate = rate;
	}

	@Override
	public int getBonus() {
		return bonus;
	}

	@Override
	public void setBonus(int bonus) {
		this.bonus = bonus;
	}

	@Override
	public void resetData() {
		account = null;
		bonus = -1;
		terraformRate = -1;
	}

}
