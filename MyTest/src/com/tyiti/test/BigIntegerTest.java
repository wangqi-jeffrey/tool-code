package com.tyiti.test;

import java.math.BigInteger;

public class BigIntegerTest {

	/**
	 * BigInteger可以表示任意精度的整数。当你使用long类型进行运算，可能会产生溢出时就要考虑使用BigInteger了。
	 * BigDecimal就使用了BigInteger作为backend。 
		那么BigInteger是如何做到可以表示任意精度的整数的？答案是使用数组来表示，看下面这个栗子就很直观了，
	 * @author Jeffrey
	 * @since 2017年11月6日 上午9:28:11
	 * @param args void
	 */
	public static void main(String[] args) {
		byte[] mag = { 2, 1 // 10 00000001 == 513
		};
		System.out.println(new BigInteger(mag));
		/**
		 * 通过byte[]来当作底层的二进制表示，例如栗子中的[2, 1]，也就是[00000010B, 00000001B]，
		 * 就是表示二进制的10 00000001B这个数，也就是513了。 
			BigInteger内部会将这个byte[]转换成int[]保存，代码在stripLeadingZeroBytes方法，
			
			上面也可以看到这个byte[]应该是big-endian two's-complement binary representation。 
			那么为什么构造函数不直接让我们扔一个int[]进去就得了呢，还要这么转换一下？答案是因为Java的整数都是有符号整数，
			举个栗子，int类型没办法表示232−1，
			也就是32位上全都是1这个数的，这时候用byte[]得这么写，(byte)255,(byte)255,(byte)255,(byte)255，这样才能表示32个1。
			加法运算比较简单，就是模拟十进制加法运算的过程，从两个加数的最低位开始加，如果有进位就进位。
			
			乘法运算要复杂一点，不过也一样是模拟十进制乘法运算，
			也就是一个乘数的每一位与另一个乘数的每一位相乘再相加（乘法运算可以拆成加法运算），所以才有那个双重的for循环。 
			最后的最后，想说的一点是，其实BigInteger可以看成是232进制的计数表示，这样就比较容易理解上面的加法跟乘法运算了。
			至于为什么是232进制？自己再想想哈^_^
		 */
	}

}
