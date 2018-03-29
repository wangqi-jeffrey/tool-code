package com.tyiti.test;

public class ForTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 5; j++) {
				if (j == 1) {
					break;
				}
				System.out.println("j:"+j);
			}
			System.out.println("==="+i);
		}
		Integer var = 1235;
		Integer var1 = 1233;
		System.out.println("================="+(var1 == var));
	}

}
