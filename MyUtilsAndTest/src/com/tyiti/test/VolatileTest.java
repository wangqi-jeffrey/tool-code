package com.tyiti.test;

/**
 * volatile的使用
 * Volatile修饰的成员变量在每次被线程访问时，都强迫从共享内存重新读取该成员的值，而且，当成员变量值发生变化时，强迫将变化的值重新写入共享内存，这样两个不同的线程在访问同一个共享变量的值时，始终看到的是同一个值。
	java语言规范指出：为了获取最佳的运行速度，允许线程保留共享变量的副本，当这个线程进入或者离开同步代码块时，才与共享成员变量进行比对，如果有变化再更新共享成员变量。这样当多个线程同时访问一个共享变量时，可能会存在值不同步的现象。
	而volatile这个值的作用就是告诉VM：对于这个成员变量不能保存它的副本，要直接与共享成员变量交互。
	建议：当多个线程同时访问一个共享变量时，可以使用volatile，而当访问的变量已在synchronized代码块中时，不必使用。
	缺点：使用volatile将使得VM优化失去作用，导致效率较低，所以要在必要的时候使用。
 * @author Jeffrey
 * @since 2017年4月10日 下午1:47:03
 */
public class VolatileTest {
	// 不使用volatile的情况 运行后，程序进入死循环了，一直在运行。
//	private static boolean bChanged;
	/**
	 *  程序输出!=，然后马上退出。
		但是，很多情况下，用不用volatile，感觉不出什么区别
		
		可以使用synchronized来对boolPattern加锁，但是synchronized开销比volatile大，volatile能够胜任上面的工作。
		volatile不保证原子操作，所以，很容易读到脏数据。
		使用建议：在两个或者更多的线程访问的成员变量上使用volatile。当要访问的变量已在synchronized代码块中，或者为常量时，不必使用。
		
		复习：
		事物的四大特性：
		⑴ 原子性（Atomicity）
		　　原子性是指事务包含的所有操作要么全部成功，要么全部失败回滚，因此事务的操作如果成功就必须要完全应用到数据库，如果操作失败则不能对数据库有任何影响。
		
		⑵ 一致性（Consistency）
		　　一致性是指事务必须使数据库从一个一致性状态变换到另一个一致性状态，也就是说一个事务执行之前和执行之后都必须处于一致性状态。
		
		　　拿转账来说，假设用户A和用户B两者的钱加起来一共是5000，那么不管A和B之间如何转账，转几次账，事务结束后两个用户的钱相加起来应该还得是5000，这就是事务的一致性。
		
		⑶ 隔离性（Isolation）
		　　隔离性是当多个用户并发访问数据库时，比如操作同一张表时，数据库为每一个用户开启的事务，不能被其他事务的操作所干扰，多个并发事务之间要相互隔离。
		
		　　即要达到这么一种效果：对于任意两个并发的事务T1和T2，在事务T1看来，T2要么在T1开始之前就已经结束，要么在T1结束之后才开始，这样每个事务都感觉不到有其他事务在并发地执行。
		
		　　关于事务的隔离性数据库提供了多种隔离级别，稍后会介绍到。
		
		⑷ 持久性（Durability）
			持久性是指一个事务一旦被提交了，那么对数据库中的数据的改变就是永久性的，即便是在数据库系统遇到故障的情况下也不会丢失提交事务的操作。

　　			例如我们在使用JDBC操作数据库时，在提交事务方法后，提示用户事务操作完成，当我们程序执行完成直到看到提示后，就可以认定事务以及正确提交，即使这时候数据库出现了问题，也必须要将我们的事务完全执行完成，否则就会造成我们看到提示事务处理完毕，但是数据库因为故障而没有执行事务的重大错误。
	 */
	private static volatile boolean bChanged;

	public static void main(String[] args) throws InterruptedException {
		new Thread() {
			@Override
			public void run() {
				for (;;) {
					if (bChanged == !bChanged) {
						System.out.println("!=");
						System.exit(0);
					}
				}
			}
		}.start();
		Thread.sleep(1);
		new Thread() {

			@Override
			public void run() {
				for (;;) {
					bChanged = !bChanged;
				}
			}
		}.start();
	}

}
