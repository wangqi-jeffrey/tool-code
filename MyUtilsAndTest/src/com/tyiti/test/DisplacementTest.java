package com.tyiti.test;

/**
 * Java位移运算
 * 
 * @author wangqi
 * @date 2016年5月10日 下午5:00:37
 * @description TODO
 */
public class DisplacementTest {
	/**
	 * java中有三种移位运算符
	 * 
	 * << : 左移运算符，num << 1,相当于num乘以2
	 * 
	 * >> : 右移运算符，num >> 1,相当于num除以2取整
	 * 
	 * >>> : 无符号右移，忽略符号位，空位都以0补齐
	 */
	public static void main(String[] args) {
		printInfo(2);
		//00000000 00000000 00000000 00000011  --左移3位--> 00000 00000000 00000000 00000011000  = 0*2^0 + 0*2^1 + 0*2^2 + 1*2^3 + 1*2^4 = 8 + 16 = 24                                              
		System.out.println(3<<3);//3*2*2*2
		//00000000 00000000 00000000 00001001  --右移2位--> 0000000000 00000000 00000000 000010  = 0*2^0 + 1*2^2 = 2
		System.out.println(9>>2);//9/2/2
		/**
		 * 无符号右移为了把高阶位置零，比如int型数i=-1是32个1组成的，如果执行i>>>24,则
			11111111 11111111 11111111 11111111    -1
			>>>
			00000000 00000000 00000000 11111111    255
			而还是上面的数通过>>>，每移一次就相当于原来的数除以2
		 */
		System.out.println(-1>>>24);
		//异或 两个操作数的位中，相同则结果为0，不同则结果为1。
		System.out.println("==========^:"+(15^2));//15，转换成二进制为1111，2，转换成二进制为0010，根据异或的运算规律，可以得出其结果为1101 即13。
		reverseVal1();
		reverseVal2();
		reverseVal3();
	}
	
	/*输出一个int的二进制数
	@param num*/
	private static void printInfo(int num){
	    System.out.println(Integer.toBinaryString(num));
	}
	
	private static void reverseVal1(){
		int a = 1, b = 2;
		//调换a b 的位置
		a = a + b;
		b = a - b;
		a = a - b;
		System.out.println("a="+a+",b="+b);
	}
	private static void reverseVal2(){
		int a = 1, b = 2;
		//调换a b 的位置
		a = a * b;
		b = a / b;
		a = a / b;
		System.out.println("a="+a+",b="+b);
	}
	private static void reverseVal3(){
		int a = 1, b = 2;
		//调换a b 的位置
		a = a ^ b; // 01 ^ 10  ->  11
		b = a ^ b; // 11 ^ 10  ->  01
		a = a ^ b; // 11 ^ 01  ->  10
		System.out.println("a="+a+",b="+b);
	}
}
