package com.tyiti.test;

import java.util.ArrayList;
import java.util.List;

public class ListTest {
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			if (i == 5) {
				list.add(0, i + "");
			} else {
				list.add(i + "");
			}
		}
		System.err.println(list);
	}
}
