package com.tyiti.test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Random;

public class UtilTest {
	public static native long currentTimeMillis();
	public static void main(String[] args) {
		String[] codeList = { "home","home", "category", "order", "user", "user-reg" };
		Arrays.sort(codeList);
		/*for (int j = 0; j < codeList.length; j++) {
			String string = codeList[j];
			System.out.println(string);
		}*/
		String[] codeList1 = new String[codeList.length];
		//先升序，后降序
		// 拷贝数组
		String[] codeList2 = Arrays.copyOf(codeList, codeList.length);
		//System.arraycopy(codeList,0,codeList1,0,codeList.length);
		for (int j = codeList2.length-1; j > -1; j--) {
			String string = codeList2[j];
			//System.out.println(string);
		}
//		System.out.println(Arrays.binarySearch(codeList, 0, codeList.length, "category"));
		//Java Arrays.binarySearch
		int [] a=new int[]{23,56,78,4,1,5879,54};
        //sort(a);
		//sort(codeList);
		/*for (int j = 0; j < codeList.length; j++) {
			String string = codeList[j];
			System.out.println(string);
		}*/
        //System.out.println(Arrays.toString(a));
//		System.out.println(Math.random());
//		System.out.println(new Random().nextInt(10000));
		System.err.println("======"+Integer.parseInt("1235465", 10));
		//System.loadLibrary("sdsdsd");
		
		System.err.println("==============="+new BigDecimal(0.00).compareTo(new BigDecimal(0)));
		
		Integer a1 = 129;
		Integer b1 = 129;
		System.out.println(a1.equals(b1));
	}
	
	private static void sort(int[] a) {
        for (int i = 0; i < a.length-1; i++) {
            for (int j = i+1; j < a.length; j++) {
                if(a[j]>a[i]){
                    int t=a[j];
                    a[j]=a[i];
                    a[i]=t;
                }
            }
        }
    }
	/**
	 * 字符串降序
	 * @param a
	 */
	private static void sort(String[] a) {
		for (int i = 0; i < a.length-1; i++) {
			for (int j = i+1; j < a.length; j++) {
				if(a[j].compareTo(a[i])>0){
					String t=a[j];
					a[j]=a[i];
					a[i]=t;
				}
			}
		}
	}
}
