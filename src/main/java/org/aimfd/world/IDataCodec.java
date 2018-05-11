package org.aimfd.world;

public interface IDataCodec<INPUT, OUTPUT> {
	OUTPUT encode();

	void decode(INPUT input);
}
