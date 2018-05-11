package org.aimfd.base;

import java.util.concurrent.ThreadFactory;

public class DaemonThreadFactory implements ThreadFactory {
	/** 线程名称 */
	private final String name;

	public DaemonThreadFactory(String name) {
		this.name = name;
	}

	/**
	 * 产生新线程
	 */
	@Override
	public Thread newThread(Runnable target) {
		Thread thread = new Thread(target, name);
		thread.setDaemon(true);
		return thread;
	}

}
