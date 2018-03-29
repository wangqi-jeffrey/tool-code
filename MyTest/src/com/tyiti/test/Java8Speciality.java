package com.tyiti.test;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Java8Speciality implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5193768036452271851L;

	public static void main(String[] args) {
		// lambda表达式
		List<String> names = Arrays.asList("a","b","c","d","a1");
		Collections.sort(names, (String a, String b) -> {
		    return b.compareTo(a);
		});
		System.out.println(names.toString());
		// 更简介的写法
		Collections.sort(names, (String a, String b) -> a.compareTo(b));
		System.out.println(names.toString());
		// 更更简介的写法
		Collections.sort(names, (a, b) -> b.compareTo(a));
		System.err.println(Integer.MAX_VALUE);
	}

}
