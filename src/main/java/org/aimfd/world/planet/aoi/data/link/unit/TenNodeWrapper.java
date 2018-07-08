package org.aimfd.world.planet.aoi.data.link.unit;

/**
 * 链表节点包装器
 * 
 * @author AIM
 *
 * @param <T>
 */
public class TenNodeWrapper<T> {
	private T value;
	protected NodeWrapper<T> nodeX = null;
	protected NodeWrapper<T> nodeY = null;

	public void setValue(T value) {
		this.value = value;
	}

	public T getValue() {
		return value;
	}

	public NodeWrapper<T> getNodeX() {
		return nodeX;
	}

	public NodeWrapper<T> getNodeY() {
		return nodeY;
	}

}
