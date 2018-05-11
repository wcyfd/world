package org.aimfd.base;

import org.springframework.context.ApplicationContext;

public class SpringContext {
	private static ApplicationContext context = null;

	public static void setApplicationContext(ApplicationContext ctx) {
		context = ctx;
	}

	public static ApplicationContext getContext() {
		return context;
	}
}
