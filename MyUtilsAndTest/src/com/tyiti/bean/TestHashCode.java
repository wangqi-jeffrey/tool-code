package com.tyiti.bean;

import java.util.HashSet;
import java.util.Set;

public class TestHashCode {

	/**
	 *  hashcode方法返回该对象的哈希码值。支持该方法是为哈希表提供一些优点，例如，java.util.Hashtable 提供的哈希表。   
		hashCode 的常规协定是：   
		在 Java 应用程序执行期间，在同一对象上多次调用 hashCode 方法时，必须一致地返回相同的整数，前提是对象上 equals 比较中所用的信息没有被修改。从某一应用程序的一次执行到同一应用程序的另一次执行，该整数无需保持一致。   
		如果根据 equals(Object) 方法，两个对象是相等的，那么在两个对象中的每个对象上调用 hashCode 方法都必须生成相同的整数结果。   
		以下情况不 是必需的：如果根据 equals(java.lang.Object) 方法，两个对象不相等，那么在两个对象中的任一对象上调用 hashCode 方法必定会生成不同的整数结果。但是，程序员应该知道，为不相等的对象生成不同整数结果可以提高哈希表的性能。   
		实际上，由 Object 类定义的 hashCode 方法确实会针对不同的对象返回不同的整数。（这一般是通过将该对象的内部地址转换成一个整数来实现的，但是 JavaTM 编程语言不需要这种实现技巧。）   
		  
		当equals方法被重写时，通常有必要重写 hashCode 方法，以维护 hashCode 方法的常规协定，该协定声明相等对象必须具有相等的哈希码。
	 */
	/*@Override
	public int hashCode() {
		return this.i % 10;
		//return super.hashCode();
	}*/
	
	private int i;  
	  
    public int getI() {  
        return i;  
    }
  
    public void setI(int i) {  
        this.i = i;  
    }
  
    @Override
	public boolean equals(Object object) {
    	if (object == null) {  
            return false;  
        }  
        if (object == this) {  
            return true;  
        }  
        if (!(object instanceof TestHashCode)) {  
            return false;  
        }  
        TestHashCode other = (TestHashCode) object;  
        if (other.getI() == this.getI()) {  
            return true;  
        }  
        return false;
	}

    /**
     * 也就是说对于两个对象，如果调用equals方法得到的结果为true，则两个对象的hashcode值必定相等；
	　　如果equals方法得到的结果为false，则两个对象的hashcode值不一定不同；
	　　如果两个对象的hashcode值不等，则equals方法得到的结果必定为false；
	　　如果两个对象的hashcode值相等，则equals方法得到的结果未知。
	在设计hashCode方法和equals方法的时候，如果对象中的数据易变，则最好在equals方法和hashCode方法中不要依赖于该字段。
     * @author Jeffrey
     * @since 2016年9月12日 下午5:19:55
     * @param args
     */
	public final static void main(String[] args) {  
    	TestHashCode a = new TestHashCode();
    	TestHashCode b = new TestHashCode();  
        a.setI(1);  
        b.setI(1);  
        Set<TestHashCode> set = new HashSet<TestHashCode>();  
        set.add(a);  
        set.add(b);  
        System.out.println("1:" + (a.hashCode() == b.hashCode()));  
        System.out.println("2:" + a.equals(b));
        System.out.println("3:" + set);
    }  

}
