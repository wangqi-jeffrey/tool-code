package com.test;

import java.util.List;

public class MyList<E> {
	public E name(E name, List<? extends E> list) {
		return name;
	}
	public void get(List<?> list) {
		System.out.println("-----------" + list);
	}
	
	public <T> T[] toArray(List<T> a) {
		return (T[]) a.toArray();
	}
}
