package org.aimfd.base;

import java.lang.reflect.Method;

public class CallFunc {
	private Method method;
	private String name;
	private int executeType;

	public void setMethod(Method method) {
		this.method = method;
	}

	public Method getMethod() {
		return method;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getExecuteType() {
		return executeType;
	}

	public void setExecuteType(int executeType) {
		this.executeType = executeType;
	}

	@Override
	public String toString() {
		return "CallFunc [method=" + method + ", name=" + name + ", executeType=" + executeType + "]";
	}

}
