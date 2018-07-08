package org.aimfd.world.planet.aoi.data.link.unit;

public class NodeWrapper<T> {
	protected NodeWrapper<T> prev = null;
	protected NodeWrapper<T> next = null;

	public void addPrev(NodeWrapper<T> node) {
		NodeWrapper<T> prev = this.prev != null ? this.prev : null;

		if (prev != null) {
			prev.next = node;
			node.prev = prev;
		}
		this.prev = node;
		node.next = this;
	}

	public NodeWrapper<T> getPrevNode() {
		return this.prev;
	}

	/**
	 * 移除前一个
	 */
	public void removePrev() {
		if (this.prev == null) {
			return;
		}

		NodeWrapper<T> prev = this.prev;
		if (prev.prev == null) {
			prev.next = null;
			this.prev = null;
		} else {
			prev.prev.next = this;
			this.prev = prev.prev;

			// 移除的节点前后置空
			prev.prev = null;
			prev.next = null;
		}
	}

	public void addNext(NodeWrapper<T> node) {
		NodeWrapper<T> next = this.next != null ? this.next : null;

		if (next != null) {
			node.next = next;
			next.prev = node;
		}

		this.next = node;
		node.prev = this;
	}

	public NodeWrapper<T> getNextNode() {
		return this.next;
	}

	/**
	 * 移除后一个
	 */
	public void removeNext() {
		NodeWrapper<T> next = this.next;
		if (next == null) {
			return;
		}

		if (next.next == null) {
			next.prev = null;
			this.next = null;
		} else {
			next.next.prev = this;
			this.next = next.next;

			next.prev = null;
			next.next = null;
		}

	}
}
