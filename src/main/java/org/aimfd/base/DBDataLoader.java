package org.aimfd.base;

public interface DBDataLoader<TABLE, ENTITY> {
	void load(TABLE table, ENTITY entity);
}
