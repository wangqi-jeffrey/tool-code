package com.tyiti.test;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class IntegerTest {

	public static void main(String[] args) {
//		method1(); 
		method2(); 
	}
	
	private static void method2() {
		System.err.println(Integer.valueOf("1233"));
	}

	private static void method1() {
		// TODO Auto-generated method stub
		// XDate.formatNow("yyyyMMddHHmmssSSS")
	    System.out.println("===="+Integer.parseInt("00010"));
	    DecimalFormat df = new DecimalFormat("0000");
	    DecimalFormat df1 = new DecimalFormat("#00");
	    String str = df1.format(123);
	    System.out.println("dssdsdds:"+str);
	    String or = "OR201606290937";
	    System.out.println("----"+Integer.parseInt(or.substring(10, or.length()))+1);
	    
	    DecimalFormat intFormat = new DecimalFormat("#");
	    System.out.println(Integer.parseInt(intFormat.format(14.0)));
	    
	    //System.out.println(Integer.parseInt("1.00"));
	    double i=2, j=2.111111, k=2.5, m=2.9; 
	    System.out.println((int)Math.floor(Double.parseDouble("111")));
	    System.out.println("舍掉小数取整:Math.floor(2)=" + (int)Math.floor(i)); 
	    System.out.println("舍掉小数取整:Math.floor(2.1)=" + (int)Math.floor(j)); 
	    System.out.println("舍掉小数取整:Math.floor(2.5)=" + (int)Math.floor(k)); 
	    System.out.println("舍掉小数取整:Math.floor(2.9)=" + (int)Math.floor(m)); 
	                                         
	    /* 这段被注释的代码不能正确的实现四舍五入取整 
	    System.out.println("四舍五入取整:Math.rint(2)=" + (int)Math.rint(i)); 
	    System.out.println("四舍五入取整:Math.rint(2.1)=" + (int)Math.rint(j)); 
	    System.out.println("四舍五入取整:Math.rint(2.5)=" + (int)Math.rint(k)); 
	    System.out.println("四舍五入取整:Math.rint(2.9)=" + (int)Math.rint(m)); 
	    
	    System.out.println("四舍五入取整:(2)=" + new DecimalFormat("0").format(i)); 
	    System.out.println("四舍五入取整:(2.1)=" + new DecimalFormat("0").format(i)); 
	    System.out.println("四舍五入取整:(2.5)=" + new DecimalFormat("0").format(i)); 
	    System.out.println("四舍五入取整:(2.9)=" + new DecimalFormat("0").format(i)); 
	    */ 
	    
	    System.out.println("四舍五入取整:(2)=" + new BigDecimal("2").setScale(0, BigDecimal.ROUND_HALF_UP)); 
	    System.out.println("四舍五入取整:(2.1)=" + new BigDecimal("2.1").setScale(0, BigDecimal.ROUND_HALF_UP)); 
	    System.out.println("四舍五入取整:(2.5)=" + new BigDecimal("2.5").setScale(0, BigDecimal.ROUND_HALF_UP)); 
	    System.out.println("四舍五入取整:(2.9)=" + new BigDecimal("2.9").setScale(0, BigDecimal.ROUND_HALF_UP));

	    System.out.println("凑整:Math.ceil(2)=" + (int)Math.ceil(i)); 
	    System.out.println("凑整:Math.ceil(2.1)=" + (int)Math.ceil(j)); 
	    System.out.println("凑整:Math.ceil(2.5)=" + (int)Math.ceil(k)); 
	    System.out.println("凑整:Math.ceil(2.9)=" + (int)Math.ceil(m));

	    System.out.println("舍掉小数取整:Math.floor(-2)=" + (int)Math.floor(-i)); 
	    System.out.println("舍掉小数取整:Math.floor(-2.1)=" + (int)Math.floor(-j)); 
	    System.out.println("舍掉小数取整:Math.floor(-2.5)=" + (int)Math.floor(-k)); 
	    System.out.println("舍掉小数取整:Math.floor(-2.9)=" + (int)Math.floor(-m)); 
	    
	    System.out.println("四舍五入取整:(-2)=" + new BigDecimal("-2").setScale(0, BigDecimal.ROUND_HALF_UP)); 
	    System.out.println("四舍五入取整:(-2.1)=" + new BigDecimal("-2.1").setScale(0, BigDecimal.ROUND_HALF_UP)); 
	    System.out.println("四舍五入取整:(-2.5)=" + new BigDecimal("-2.5").setScale(0, BigDecimal.ROUND_HALF_UP)); 
	    System.out.println("四舍五入取整:(-2.9)=" + new BigDecimal("-2.9").setScale(0, BigDecimal.ROUND_HALF_UP));

	    System.out.println("凑整:Math.ceil(-2)=" + (int)Math.ceil(-i)); 
	    System.out.println("凑整:Math.ceil(-2.1)=" + (int)Math.ceil(-j)); 
	    System.out.println("凑整:Math.ceil(-2.5)=" + (int)Math.ceil(-k)); 
	    System.out.println("凑整:Math.ceil(-2.9)=" + (int)Math.ceil(-m));
	}

}
