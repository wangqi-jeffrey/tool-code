package com.tyiti.test;

import java.util.regex.Pattern;

public class RegTest {
	public static final String SRE_NEGATIVE_DIGIT = "^(-?)\\d+$";
	public static final Pattern DIGIT = Pattern.compile(SRE_NEGATIVE_DIGIT);
	public static void main(String[] args) {
		//System.out.println(DIGIT.matcher("0").matches());
		
		System.out.println("10".matches("^[1-9]{1}|[1-9]{1}[0-9]+$"));
	}
}
