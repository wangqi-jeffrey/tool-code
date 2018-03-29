package com.test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
	Class<? extends Main> obj = this.getClass();
	public static void main(String[] args) {
//		MyList<List> list = new MyList<List>(); // 默认传入Object
//		System.out.println(list.name(new ArrayList<List>(), new LinkedList<List>()));
//		list.get(new ArrayList<String>());
//		System.out.println("======" + list.toArray(new ArrayList<String>()));
	}
	
	public static void testCatch() {
		try {
			
		} catch (NullPointerException e) {
			// TODO: handle exception
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
