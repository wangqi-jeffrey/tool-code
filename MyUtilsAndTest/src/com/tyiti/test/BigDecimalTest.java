package com.tyiti.test;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.NumberFormat;
import java.text.ParseException;

public class BigDecimalTest {
	public static void main(String[] args) throws ParseException {
		//System.out.println(new BigDecimal(299).compareTo(new BigDecimal(1).multiply(new BigDecimal(299))));
		//System.out.println(new BigDecimal(0));
		BigDecimal aa = new BigDecimal(0.00);
		//aa = new BigDecimal(0.22300);
		//aa = aa.setScale(2,BigDecimal.ROUND_DOWN);
		//System.out.println(aa);
		NumberFormat nf = NumberFormat.getInstance();
		//Number number = nf.parse("0.3000");
		//System.out.println(number.toString());
		//System.out.println(nf.format(aa));
//		System.out.println(new BigDecimal(nf.parse(nf.format(aa)).toString()));
//		BigDecimal bb = null;
//		System.out.println(aa.equals(bb));
//		BigDecimal b = new BigDecimal("1");
//		System.out.println(b.intValue());
//		System.out.println(BigInteger.ZERO);
		testIEEE754();
	}
	
	/**
	 * IEEE 754
	 * 浮点数的精度问题
	 * IEEE二进制浮点数算术标准（IEEE 754）是20世纪80年代以来最广泛使用的浮点数运算标准，为许多CPU与浮点运算器所采用。
	 * 这个标准定义了表示浮点数的格式（包括负零-0）与反常值（denormal number）），一些特殊数值（无穷（Inf）与非数值（NaN）），
	 * 以及这些数值的“浮点数运算符”；它也指明了四种数值舍入规则和五种异常状况（包括异常发生的时机与处理方式）。
	 * @author Jeffrey
	 * @since 2017年10月26日 上午10:54:47 void
	 */
	private static void testIEEE754() {
//		printBits(3.5);
//		testFloat();
//		testBigDecmail();
		testScale();
	}
	
	/**
	 * 指数大小为10000000000B-1023=1，尾数为1.11B，所以实际数值大小为11.1B=3.5，妥妥的。 
		有一点需要注意的是上述格式为归约形式，所以尾数的整数部分为1，而当非归约形式时，尾数的整数部分是为0的。
	 * @author Jeffrey
	 * @since 2017年10月26日 下午2:48:41
	 * @param d void
	 */
	private static void printBits(double d) {
		System.out.println("##" + d);
		long l = Double.doubleToLongBits(d);
		String bits = Long.toBinaryString(l);
		int len = bits.length();
		System.out.println(bits + "#" + len);
		if (len == 64) {
			System.out.println("[63]" + bits.charAt(0));
			System.out.println("[62-52]" + bits.substring(1, 12));
			System.out.println("[51-0]" + bits.substring(12, 64));
		} else {
			System.out.println("[63]0");
			System.out.println("[62-52]" + pad(bits.substring(0, len - 52)));
			System.out.println("[51-0]" + bits.substring(len - 52, len));
		}
		// 指数大小为10000000000B-1023=1，尾数为1.11B，所以实际数值大小为11.1B=3.5，妥妥的。 
		// 有一点需要注意的是上述格式为归约形式，所以尾数的整数部分为1，而当非归约形式时，尾数的整数部分是为0的。
		//上面我们使用的浮点数3.5刚好可以准确的用二进制来表示，21+20+2−1，但并不是所有的小数都可以用二进制来表示，例如，0.1。-->testFloat()
	}
	
	/**
	 * 0.1无法表示成2x+2y+… 这样的形式，尾数部分后面应该是1100一直循环下去（纯属猜测，不过这个应该也是可以证明的），
	 * 但是由于计算机无法表示这样的无限循环，
	 * 所以就需要截断，这就是浮点数的精度问题。精度问题会带来一些unexpected的问题，例如0.1 + 0.1 + 0.1 == 0.3将会返回false，
	 * 那么BigDecimal又是如何解决这个问题的？ -->testBigDecmail()
	 * @author Jeffrey
	 * @since 2017年11月6日 上午9:20:38 void
	 */
	private static void testFloat() {
		System.out.println(0.1 + 0.1 == 0.2); // true
        System.out.println(0.1 + 0.1 + 0.1 == 0.3); // false
	}
	
	/**
	 * BigDecimal的解决方案就是，不使用二进制，而是使用十进制（BigInteger）+小数点位置(scale)来表示小数，
	 * @author Jeffrey
	 * @since 2017年11月6日 上午9:21:03 void
	 */
	private static void testBigDecmail() {
		BigDecimal bd = new BigDecimal("100.001");
        System.out.println(bd.scale());
        System.out.println(bd.unscaledValue());
        /**
         * 也就是100.001 = 100001 * 0.1^3。这种表示方式下，避免了小数的出现，当然也就不会有精度问题了。
         * 十进制，也就是整数部分使用了BigInteger来表示，小数点位置只需要一个整数scale来表示就OK了。 
			当使用BigDecimal来进行运算时，也就可以分解成两部分，BigInteger间的运算，以及小数点位置scale的更新，
			下面先看下运算过程中scale的更新。-->testScale()
         */
	}
	
	/**
	 * 加法运算时，根据下面的公式scale更新为两个BigDecimal中较大的那个scale即可。
	 * X*0.1n + Y*0.1m == X*0.1n + (Y*0.1m−n) * 0.1n == (X+Y*0.1m−n) * 0.1n，其中n>m
	 * 
	 * 乘法运算根据下面的公式也可以确定scale更新为两个scale之和。
	 * X*0.1n * Y*0.1m == (X*Y)*0.1n+m
	 * @author Jeffrey
	 * @since 2017年11月6日 上午9:25:08 void
	 */
	private static void testScale() {
		
	}

	private static String pad(String exp) {
		int len = exp.length();
		if (len == 11) {
			return exp;
		} else {
			StringBuilder sb = new StringBuilder();
			for (int i = 11 - len; i > 0; i--) {
				sb.append("0");
			}
			sb.append(exp);
			return sb.toString();
		}
	}
}
