package org.aimfd.base;

public class ExecutorCenter {
	public final static HashExecutor logicExecutor = new HashExecutor("logic", 1);
	public final static HashExecutor loginExecutor = new HashExecutor("login", 8);
	public final static HashExecutor matchExecutor = new HashExecutor("match", 1);
}
