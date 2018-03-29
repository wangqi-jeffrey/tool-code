package com.tyiti.test;

import java.util.ArrayList;

public class ArrayListTest {

	public static void main(String[] args) {
		MyList<String> list = new MyList();
		list.add("123");
		list.add("456");
		list.printElement();
	}

}

class MyList<E> extends ArrayList<E> {
	private int sum;
	private int sizes;
	
	@Override
	public boolean add(E e) {
		// TODO Auto-generated method stub
		if (e instanceof String) {
			e = (E) "a".concat(e.toString());
		}
		return super.add(e);
	}
	
	@Override
	public int size() {
		return super.size();
	}
	
	public void printElement() {
		for (int i = 0; i < this.size(); i++) {
			System.out.println("-----" + this.get(i));
		}
		//System.out.println("---" + super.forEach(action););
	}
}
