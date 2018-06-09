package org.aimfd.world.system.match.data;

public interface IMatchData {

	/**
	 * 队伍中是否包含该玩家
	 * 
	 * @param account
	 * @return
	 */
	boolean contains(String account);

	/**
	 * 添加
	 * 
	 * @param account
	 */
	void addAccount(String account);

	/**
	 * 删除
	 * 
	 * @param account
	 */
	void removeAccount(String account);

	/**
	 * 获得队伍长度
	 * 
	 * @return
	 */
	int getQueueLength();

	/**
	 * 
	 * 
	 * @param count
	 * @return
	 */
	String poll();
}
