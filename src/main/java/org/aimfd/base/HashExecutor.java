package org.aimfd.base;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;

/**
 * 利用HASH方法进行任务处理，保证每个任务都走自己的线程
 * 
 * @author AIM
 *
 */
public class HashExecutor {
	private Logger logger;
	private int mask;
	private String name;

	private ExecutorService[] executors;

	public HashExecutor(String name, int num) {
		this.name = name;
		this.mask = num - 1;

		executors = new ExecutorService[num];
		for (int i = 0; i < num; i++) {
			executors[i] = Executors.newSingleThreadExecutor();
		}
	}

	public void execute(int hashId, Runnable task) {
		int threadID = hashId & mask;

		try {
			executors[threadID].execute(task);
		} catch (Exception e) {
			if (logger != null) {
				if (e instanceof RejectedExecutionException) {
					logger.debug("线程[{}]编号[{}]已关闭，拒绝任务", name, threadID);
				} else {
					logger.debug("线程[{}]编号[{}]执行异常", name, threadID, e.getStackTrace().toString());
				}
			}

		}
	}

	public Future<?> submit(int hashId, Runnable task) {
		return executors[hashId & mask].submit(task);
	}

	public void awaitTerminate(long time) {
		for (int i = 0; i <= mask; i++) {
			try {
				logger.info("终止线程执行器,名称={},编号={}", name, i);
				executors[i].shutdown();

				executors[i].awaitTermination(time, TimeUnit.SECONDS);
			} catch (InterruptedException e) {
				logger.error("线程执行器在关闭过程中被中断,名称={},编号={}", name, i);
			}
			// 是否执行完毕
			if (executors[i].isTerminated()) {
				List<Runnable> runList = executors[i].shutdownNow();
				logger.error("线程执行其在规定时间内没有正常执行完毕,名称={},编号={},未执行完任务数量={}", name, i, runList.size());
				for (Runnable r : runList) {
					logger.error("未执行的任务:{}", r);
				}
				continue;
			}
			logger.info("线程执行器在规定时间内正常执行完毕,名称={},编号={}", name, i);
		}
	}

}
