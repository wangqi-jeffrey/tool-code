package com.tyiti.test.enumTest;

import java.util.ArrayList;
import java.util.List;

import com.tyiti.test.enumTest.TestEnum.ColorEnum;
import com.tyiti.test.enumTest.TestEnum.OrderState;
import com.tyiti.test.enumTest.TestEnum.Property;
import com.tyiti.test.enumTest.TestEnum.Source;

public class Test {
	public static void main(String[] args) {
		// 枚举是一种类型，用于定义变量，以限制变量的赋值；赋值时通过“枚举名.值”取得枚举中的值
		ColorEnum colorEnum = ColorEnum.blue;
		switch (colorEnum) {
		case red:
			System.out.println("color is red");
			break;
		case green:
			System.out.println("color is green");
			break;
		case yellow:
			System.out.println("color is yellow");
			break;
		case blue:
			System.out.println("color is blue");
			break;
		}
		System.out.println(Source.PLAT.getKeyAndValue());
		System.err.println(Property.PRO1.getPro2());
		System.err.println(OrderState.CANCEL.getName());
		
		
		List<String> list = new ArrayList<String>(3);
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("54");
		list.add("524");
		System.err.println(list.get(4));
		Integer i = new Integer(1022222222);
		System.out.println(i.shortValue());
	}
}
