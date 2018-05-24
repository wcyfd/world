package org.aimfd.base;

public interface IDataCodec<INPUT, OUTPUT> {
	OUTPUT encode();

	void decode(INPUT source);
}
