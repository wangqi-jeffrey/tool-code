package com.tyiti.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class OtherTest {
	public static void main(String[] args) {
		String aaa = "111-222-333";
		//System.out.println(aaa.split("-")[0]);
		//System.out.println(aaa.split("-")[1]);
		//System.out.println(aaa.split("-")[2]);
		//System.out.println(String.valueOf("123456789".substring(2,"123456789".length())));
		//System.out.println("c07cbc39680b392b1c5a0e2b3fd7ad0df320525d".equals("C07CBC39680B392B1C5A0E2B3FD7AD0DF320525D".toLowerCase()));
		String ss = "123#44444";
		//System.out.println(ss.substring(0,ss.indexOf("#")));
//		Map<Integer, String> map = new HashMap<Integer, String>();
//		map.put(1, "value1");
//		map.put(1, "value2");
//		String resValue = map.put(1, "value");
//		map.put(null, "111");
//		System.err.println(resValue+"===="+map.get(1)+"===="+map.get(null));
		
		System.out.println("=========time:"+new SimpleDateFormat("HH:mm:ss").format(new Date()));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < 3032*3032; i++) {
			System.out.println("====="+i);
		}
		System.out.println("=========time:"+new SimpleDateFormat("HH:mm:ss").format(new Date()));
	}
}
