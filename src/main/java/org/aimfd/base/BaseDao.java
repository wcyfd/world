package org.aimfd.base;

import java.io.Serializable;

public interface BaseDao<T, ID extends Serializable> {
	ID insert(T t);

	int delete(ID id);

	int update(T t);

	T find(ID id);

}
