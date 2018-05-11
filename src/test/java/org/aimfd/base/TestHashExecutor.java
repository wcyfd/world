package org.aimfd.base;

public class TestHashExecutor {
	private HashExecutor hashExecutor;
	private StringBuffer sb = new StringBuffer();

	private void ready() {
		hashExecutor = new HashExecutor("test", 4);
	}

	public void testHashExecutor() {
		for (int i = 0; i < 5; i++) {
			String name = "name" + i;
			Runnable run = new TestTask(name);
			System.out.println("create ========== " + name);
			for (int j = 0; j < 5; j++) {
				hashExecutor.execute(name.hashCode(), run);
			}
		}

	}

	final class TestTask implements Runnable {

		private String name;

		public TestTask(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		@Override
		public void run() {
			long threadId = Thread.currentThread().getId();
			System.out.println(name + "->" + threadId);
		}
	}

	public static void main(String[] args) {

		TestHashExecutor executor = new TestHashExecutor();
		executor.ready();
		executor.testHashExecutor();
	}
}
