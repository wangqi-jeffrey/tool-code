package com.tyiti.test;

import java.util.HashSet;
import java.util.Set;

public class SetTest {

	public static void main(String[] args) {
		//method1();
		method2();
	}

	private static void method2() {
		Set<String> set = new HashSet<String>();
		String a1 = "1569";
		String a2 = "1569";
		System.out.println(set.add(a1));
		System.out.println(set.add(a2));
	}
	private static void method1() {
		Set<Integer> set = new HashSet<Integer>();
		Integer a1 = 1569;
		Integer a2 = 1569;
		Integer a3 = new Integer(1569);
		set.add(a1);
		set.add(a2);
		set.add(a3);
		System.out.println((a1 == a2) + "====" + a1.equals(a2));
		System.err.println("-----"+set.size());
	}

}
