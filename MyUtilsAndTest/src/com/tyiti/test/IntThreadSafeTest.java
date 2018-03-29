package com.tyiti.test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * int类型线程安全测试 在Java中，高并发/多线程情况下，int的自增自减操作都不是线程安全的，使用AtomicInteger可以保证。
 * @author Jeffrey
 * @since 2017年4月10日 上午11:04:41
 */
public class IntThreadSafeTest {
	private int shareI = 0;
	
	private AtomicInteger atomicLoop = new AtomicInteger(0);
	private int loop = 0;

	public static void main(String[] args) throws Exception {
		IntThreadSafeTest t = new IntThreadSafeTest();
		t.testMethod();
		System.out.println(t);
	}

	public void testMethod() throws Exception {
		Thread th1 = new Thread(new ThreadTest());
		Thread th2 = new Thread(new ThreadTest());
		th1.start();
		th2.start();
		th1.join();
		th2.join();
	}

	public synchronized void add() {
		shareI++;
	}

	class ThreadTest implements Runnable {
		@Override
		public void run() {
			//testAtomicInteger();
			testInt();
		}
	}

	public void testInt() { // 测试，输出结果随机，为线程不安全
		for (; loop < 100000; loop++) {
			add();
		}
	}

	public void testAtomicInteger() { // 输出1000000，线程安全
		for (; atomicLoop.getAndAdd(1) < 100000;) {
			add();
		}
	}

	@Override
	public String toString() {
		return "" + shareI;
	}
}

/**
 * AtomicIntegerFieldUpdater可以对Obj的字段进行处理
 * 
 * @author Jeffrey
 * @since 2017年4月10日 下午2:21:34
 */
class TestAtomicObjectInteger {
	private Looper looper = new Looper();
	private int shareI = 0;

	public static void main(String... args) throws Exception {
		TestAtomicObjectInteger t = new TestAtomicObjectInteger();
		t.testMethod();
		System.out.println(t);
	}

	public void testNormal() { // 线程不安全的
		for (; looper.getLoop() < 100000; looper.setLoop(looper.getLoop() + 1)) {
			add();
		}
	}

	public void testUseAtomic() {
		AtomicIntegerFieldUpdater<Looper> atomicIntegerFieldUpdater = // 使用Atomic来进行自加
		AtomicIntegerFieldUpdater.newUpdater(Looper.class, "loop");
		for (; atomicIntegerFieldUpdater.getAndAdd(looper, 1) < 100000;) {
			add();
		}
	}

	public void testMethod() throws Exception {
		Thread th1 = new Thread(new ThreadTest());
		Thread th2 = new Thread(new ThreadTest());
		th1.start();
		th2.start();
		th1.join();
		th2.join();
	}

	class ThreadTest implements Runnable {
		@Override
		public void run() {
			testUseAtomic();
			// testNormal();
		}
	}

	public synchronized void add() {
		shareI++;
	}

	@Override
	public String toString() {
		return shareI + "";
	}
}

class Looper {
	public volatile int loop = 0; // 对于AtomicIntegerFieldUpdater要控制的字段必须为public volatile

	public int getLoop() {
		return loop;
	}

	public void setLoop(int loop) {
		this.loop = loop;
	}
}
