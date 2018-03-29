package com.tyiti.test.enumTest;

/**
 * 枚举用法详解
 */
public class TestEnum {
	/**
	 * 普通枚举
	 */
	public enum ColorEnum {
		red, green, yellow, blue;
	}

	/**
	 * 枚举像普通的类一样可以添加属性和方法，可以为它添加静态和非静态的属性或方法
	 */
	public enum SeasonEnum {
		// 注：枚举写在最前面，否则编译出错
		spring, summer, autumn, winter;

		private final static String position = "test";

		public static SeasonEnum getSeason() {
			if ("test".equals(position))
				return spring;
			else
				return winter;
		}
	}

	/**
	 * 性别
	 * 实现带有构造器的枚举
	 */
	public enum Gender {
		// 通过括号赋值,而且必须带有一个参构造器和一个属性跟方法，否则编译出错
		// 赋值必须都赋值或都不赋值，不能一部分赋值一部分不赋值；如果不赋值则不能写构造器，赋值编译也出错
		MAN("MAN"), WOMEN("WOMEN");

		private final String value;

		// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
		Gender(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	/**
	 * 订单状态
	 * 实现带有抽象方法的枚举
	 */
	public enum OrderState {
		/** 已取消 */
		CANCEL {
			public String getName() {
				return "已取消";
			}
		},
		/** 待审核 */
		WAITCONFIRM {
			public String getName() {
				return "待审核";
			}
		},
		/** 等待付款 */
		WAITPAYMENT {
			public String getName() {
				return "等待付款";
			}
		},
		/** 正在配货 */
		ADMEASUREPRODUCT {
			public String getName() {
				return "正在配货";
			}
		},
		/** 等待发货 */
		WAITDELIVER {
			public String getName() {
				return "等待发货";
			}
		},
		/** 已发货 */
		DELIVERED {
			public String getName() {
				return "已发货";
			}
		},
		/** 已收货 */
		RECEIVED {
			public String getName() {
				return "已收货";
			}
		};

		public abstract String getName();
	}
	
	public enum OperateModel {
		ORDER(1,"订单"), CANCELLATION(2,"退货"), REFUND(3,"退款"),LEVMSG(4,"用户留言"),COUPON(5,"优惠券");
		
		private int  key ;
		private String value;
		public int getKey() {
			return key;
		}
		public void setKey(int key) {
			this.key = key;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		private OperateModel(int key, String value) {
			this.key = key;
			this.value = value;
		}
		 
	}
	
	public enum Action {
		TIJIAODINGDAN(1,"提交订单"), 
		WEINXIN(2,"微信支付"),
		SHENCHENGFENQI(3,"生成分期"),
		QUEREN(4,"确认"),
		ZHIDAN(5,"制单"),
		FAHUO(6,"发货"),
		QIANSHOU(7,"用户签收"),
		QUXIAO(8,"取消"),
		SHENQINGQUXIAO(9,"申请取消"),
		SHENQINGTUIHUO(10,"申请退货"),
		QUEDINGTUIHUO(11,"确定退货"),
		JUJUETUIHUO(12,"拒绝退货"),
		SHOUHUO(13,"收货"),
		REFUND(14,"退款"),
		TONGYIQUXIAO(15,"同意申请取消订单"),
		ORDERFINISH(16,"订单完成"),
		ADD(17,"添加"),
		REMOVE(18,"删除"),
		UPDATE(19,"修改");
		private int key ;
		private String value;
		private Action(int key, String value) {
			this.key = key;
			this.value = value;
		}
		
		public int getKey() {
			return key;
		}
		public void setKey(int key) {
			this.key = key;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		
	}
	public enum Source {
		SHOP(1,"商城") {
			@Override
			public String getKeyAndValue() {
				// TODO Auto-generated method stub
				return null;
			}
		},PLAT(2,"平台") {
			@Override
			public String getKeyAndValue() {
				return this.getKey()+"=="+this.getValue();
			}
		};
		private int key ;
		private String value;
		private Source(int key, String value) {
			this.key = key;
			this.value = value;
		}
		
		public int getKey() {
			return key;
		}
		public void setKey(int key) {
			this.key = key;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public abstract String getKeyAndValue();
	}
	public enum Property {
		PRO1(1,"属性1","属性2"),PRO2(2,"属性1","属性2");
		private int key ;
		private String pro1;
		private String pro2;
		private Property(int key, String pro1, String pro2) {
			this.key = key;
			this.pro1 = pro1;
			this.pro2 = pro2;
		}
		public int getKey() {
			return key;
		}
		public void setKey(int key) {
			this.key = key;
		}
		public String getPro1() {
			return pro1;
		}
		public void setPro1(String pro1) {
			this.pro1 = pro1;
		}
		public String getPro2() {
			return pro2;
		}
		public void setPro2(String pro2) {
			this.pro2 = pro2;
		}
	}

	public static void main(String[] args) {
		// 枚举是一种类型，用于定义变量，以限制变量的赋值；赋值时通过“枚举名.值”取得枚举中的值
		ColorEnum colorEnum = ColorEnum.blue;
		switch (colorEnum) {
		case red:
			System.out.println("color is red");
			break;
		case green:
			System.out.println("color is green");
			break;
		case yellow:
			System.out.println("color is yellow");
			break;
		case blue:
			System.out.println("color is blue");
			break;
		}

		// 遍历枚举
		System.out.println("遍历ColorEnum枚举中的值");
		for (ColorEnum color : ColorEnum.values()) {
			System.out.println(color);
		}

		// 获取枚举的个数
		System.out.println("ColorEnum枚举中的值有" + ColorEnum.values().length + "个");

		// 获取枚举的索引位置，默认从0开始
		System.out.println(ColorEnum.red.ordinal());// 0
		System.out.println(ColorEnum.green.ordinal());// 1
		System.out.println(ColorEnum.yellow.ordinal());// 2
		System.out.println(ColorEnum.blue.ordinal());// 3

		// 枚举默认实现了java.lang.Comparable接口
		System.out.println(ColorEnum.red.compareTo(ColorEnum.green));// -1

		// --------------------------
		System.out.println("===========");
		System.err.println("季节为" + SeasonEnum.getSeason());

		// --------------
		System.out.println("===========");
		for (Gender gender : Gender.values()) {
			System.out.println(gender.value);
		}

		// --------------
		System.out.println("===========");
		for (OrderState order : OrderState.values()) {
			System.out.println(order.getName());
		}
	}
}
