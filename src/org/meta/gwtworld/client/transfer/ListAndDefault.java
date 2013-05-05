package org.meta.gwtworld.client.transfer;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class ListAndDefault<T> implements Serializable {
	public List<T> list;
	public T def;

	public ListAndDefault() {
	}
	public ListAndDefault(List<T> list,T def) {
		this.list=list;
		this.def=def;
	}
}
