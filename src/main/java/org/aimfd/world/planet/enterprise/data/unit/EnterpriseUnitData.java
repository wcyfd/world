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
	public int getTr() {
		return tr;
	}

	@Override
	public void setTr(int rate) {
		this.tr = rate;
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
	public int getIron() {
		return iron;
	}

	@Override
	public void setIron(int iron) {
		this.iron = iron;
	}

	@Override
	public int getTi() {
		return ti;
	}

	@Override
	public void setTi(int ti) {
		this.ti = ti;
	}

	@Override
	public void resetData() {
		account = null;
		bonus = -1;
		tr = -1;
		iron = -1;
		ti = -1;
	}

}
