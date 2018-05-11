package org.aimfd.base;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(FIELD)
public @interface Column {
	String value();

	Class<?> paramType();

	/**
	 * 主键
	 * 
	 * @return
	 */
	boolean primaryKey() default false;
}
