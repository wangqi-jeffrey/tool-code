package com.tyiti.test;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;

public class StringTest {

	public static void main(String[] args) {
		testFormat();
	}

	private static void m1() {
		String ss = "93001002,930021001,31212";
		System.out.println(ss.indexOf("93001002"));
		System.out.println(ss.contains("1493001002"));
		String str = "110022";
		//System.out.println(str.substring(0,4));
		//methodTest1();
		//System.out.println("aaaaa-bbbbb".split("-")[0]);
		String a = "高鹤飞(1461)： 导入数据；2015-11-1 8:21:53<br/>张海艳(1369)： 库存批量确认：有货-代发货；2015-11-2 8:53:57<br/>李玲(1414)： 订单确认：订单状态：订单确认；订单类型：常规订单；2015-11-2 10:53:18<br/>张海艳(1369)： 库存确认：有货；2015-11-2 14:43:05<br/>制单员(1381)： 导出制单：2015-11-2 14:59:06<br/>制单员(1381)： 已发货； 发货方式：顺丰； 发货单号：568776315665； 发货日期：2015-11-3；操作时间：2015-11-3 17:40:10<br/>";
		//a.replaceAll("-", "<br/>");
		/**
		 * 导入订单(导入数据)，订单确认(订单确认)，制单(导出制单)，已发货(已发货)  其他的都为 备注
		 * 高鹤飞(1461)： 导入数据；2015-11-1 8:21:53
		         张海艳(1369)： 库存批量确认：有货-代发货；2015-11-2 8:53:57
		         李玲(1414)： 订单确认：订单状态：订单确认；订单类型：常规订单；2015-11-2 10:53:18
		         张海艳(1369)： 库存确认：有货；2015-11-2 14:43:05
		         制单员(1381)： 导出制单：2015-11-2 14:59:06
		         制单员(1381)： 已发货； 发货方式：顺丰； 发货单号：568776315665； 发货日期：2015-11-3；操作时间：2015-11-3 17:40:10
		 *
		/*for (int i = 0; i < a.split("<br/>").length; i++) {
			System.out.println(a.split("<br/>")[i]);
		}*/
		String b = "制单员(1381)： 已发货； 发货方式：顺丰； 发货单号：568776315665； 发货日期：2015-11-3；操作时间：2015-11-3 17:40:10";
		for (int i = 0; i < a.split("：").length; i++) {
			System.out.println("姓名：" + a.split("：")[0]);
			String str1 = a.substring(a.split("：")[0].length() + 1);
			System.out.println("剩余：" + str1);
			String str2 = str1.substring(str1.lastIndexOf("2015"), str1.length());
			System.out.println("时间：" + str2);
			String str3 = str1.substring(0,str1.length() - str2.length());
			System.out.println("===" + str3);
		}
	}
	
	private static void methodTest(){
		String ss = "Baa";
		System.err.println(ss.equalsIgnoreCase("bAA"));
	}
	private static void methodTest2(){
		String ss = "aaa/bbb/ccc";
		System.err.println(ss.endsWith("ccc"));
	}
	private static void methodTest1(){
		/*DecimalFormat intFormat = new DecimalFormat("#");
		String str = "abcdefghijklmn";
		for (int i = 0; i < 10; i++) {
			str += str;
		}
		if (str.length()>127) {
			System.err.println(str.substring(0,124)+"...");
		}
		methodTest();
		
		String tpxDays = "13";
		System.out.println("---"+tpxDays.matches("^[1-9]*$"));
		System.out.println("======flag:"+(false&&false&&true));
		
		Set<String> set = new HashSet<String>();
		System.out.println(set.add("a"));
		System.out.println(set.add("a"));
		//System.out.println(intFormat.format("321"));
		//System.out.println(String.valueOf(null));
		System.out.println(String.valueOf(" sds   ").trim());
		String src = "04544.weew";
		if(src.indexOf('.')!=-1){
			System.out.println(src.substring(0, src.indexOf('.')));
		}else{
			System.out.println(src);
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 10; i++) {
			sb.append(i+",");
		}
		System.out.println("======"+sb.toString()+"s:"+sb.length());
		System.out.println("======"+sb.substring(0, sb.length()-1));*/
	}
	
	/**
	 * %s 字符串类型  "mingrisoft"  
	 * %c 字符类型  'm'  
	 * %b 布尔类型  true  
	 * %d 整数类型（十进制） 99 
	 * %x 整数类型（十六进制） FF 
	 * %o 整数类型（八进制） 77 
	 * %f 浮点类型 99.99 
	 * %a 十六进制浮点类型 FF.35AE 
	 * %e 指数类型 9.38e+5 
	 * %g 通用浮点类型（f和e类型中较短的） 
	 * %h 散列码 
	 * %% 百分比类型 ％ 
	 * %n 换行符 
	 * %tx 日期与时间类型（x代表不同的日期与时间转换符
	 * @author Jeffrey
	 * @since 2017年10月24日 上午11:32:06 void
	 */
	public static void testFormat() {
		String str = null;
		str = String.format("Hi,%s", "王力");
		System.out.println(str);
		str = String.format("Hi,%s:%s.%s", "王南", "王力", "王张");
		System.out.println(str);
		System.out.printf("字母a的大写是：%c %n", 'A');
		System.out.printf("3>7的结果是：%b %n", 3 > 7);
		System.out.printf("100的一半是：%d %n", 100 / 2);
		System.out.printf("100的16进制数是：%x %n", 100);
		System.out.printf("100的8进制数是：%o %n", 100);
		System.out.printf("50元的书打8.5折扣是：%f 元%n", 50 * 0.85);
		System.out.printf("上面价格的16进制数是：%a %n", 50 * 0.85);
		System.out.printf("上面价格的指数表示：%e %n", 50 * 0.85);
		System.out.printf("上面价格的指数和浮点数结果的长度较短的是：%g %n", 50 * 0.85);
		System.out.printf("上面的折扣是%d%% %n", 85);
		System.out.printf("字母A的散列码是：%h %n", 'A');
	} 
}
