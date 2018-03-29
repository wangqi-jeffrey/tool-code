package com.tyiti.test;

import com.tyiti.bean.CouponRecord;
import com.tyiti.utils.JSONHelper;

public class JsonTest {
	public static void main(String[] args) {
		System.out.println(JSONHelper.bean2json(CouponRecord.class));
	}
}
