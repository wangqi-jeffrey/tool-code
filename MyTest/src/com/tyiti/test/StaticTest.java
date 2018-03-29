package com.tyiti.test;

class StaticTest1 {
	 /** 构造方法*/  
    public StaticTest1() {  
        System.out.println("构造函数被执行了!");  
    }  
    /** 静态代码快*/  
    static {  
        System.out.println("静态代码块被执行了!");  
        //show();
    }  
    /** 静态方法*/  
    public static void show(){  
        System.out.println("静态方法被执行了!");  
    }
}
public class StaticTest{
	public static void main(String[] args) throws ClassNotFoundException {
    	//StaticTest.show();
    	//加载类  
    	//Class.forName("com.tyiti.test.StaticTest");
    	//true为false的时候，就是告诉JVM不需再load class之后进行初始化的工作,这样，将初始化的工作放到newInstance的时候进行。所以，static块被执行的时机是在类被初始化的时候，无论这个类你加载多少次，static块仅仅只是被调用一次。
    	Class.forName("com.tyiti.test.StaticTest1",false,StaticTest1.class.getClassLoader());
    	/*Class<StaticTest> clazz = (Class<StaticTest>) Class.forName("com.tyiti.test.StaticTest",true,StaticTest.class.getClassLoader());
    	System.out.println("getName:"+clazz.getName());
    	System.out.println("isAnnotation:"+clazz.isAnnotation());
    	System.out.println("desiredAssertionStatus:"+clazz.desiredAssertionStatus());
    	System.out.println("getCanonicalName:"+clazz.getCanonicalName());
    	System.out.println("getModifiers:"+clazz.getModifiers());
    	System.out.println("getSimpleName:"+clazz.getSimpleName());
    	System.out.println("hashCode:"+clazz.hashCode());*/
	}
}
