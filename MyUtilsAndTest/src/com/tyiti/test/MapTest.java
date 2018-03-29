package com.tyiti.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapTest {
	public static void main(String[] args) {
		Map<String,Object> map = new HashMap<String, Object>();
		List<String> str = (List<String>) map.get("aaa");
		System.err.println(str);
	}
}
