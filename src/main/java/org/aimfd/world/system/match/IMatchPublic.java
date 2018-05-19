package org.aimfd.world.system.match;

public interface IMatchPublic {
	/**
	 * 开始匹配
	 * 
	 * @param account
	 */
	void beginMatch(String account);

	/**
	 * 取消匹配
	 * 
	 * @param account
	 */
	void cancelMatch(String account);
}
