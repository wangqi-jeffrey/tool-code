package com.tyiti.test;

public class ExceptionTest {
	public static void main(String[] args) {
		try {
			throw new RuntimeException();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println("-------------");
	}
}
