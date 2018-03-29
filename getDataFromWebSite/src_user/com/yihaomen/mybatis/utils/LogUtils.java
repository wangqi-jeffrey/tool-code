package com.yihaomen.mybatis.utils;

import java.lang.reflect.Array;

import net.sf.json.JSONObject;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class LogUtils {
	/**
	 * 日志信息模版方法
	 * @param methodName 方法名
	 * @param model 操作模块 如：订单
	 * @param description 功能描述 如：下单    订单详情
	 * @param userid 当前操作用户id，没有则传null
	 * @param reqParam 请求参数（传入重要参数即可） 如：info("订单","下单","1","id="+id+",name="+name); 无请求参数请传null
	 * @return 
	 */
	public static String info(String model,String description,String userid,String reqParam){
		StringBuffer sb = new StringBuffer("[操作模块：");
		sb.append(model);
		sb.append("    描述：");
		sb.append(description);
		sb.append("    用户信息：id=");
		sb.append(userid);
		sb.append("    参数：");
		sb.append(reqParam);
		sb.append("]");
		return sb.toString();
	}
	/**
	 * 
	 * @param model
	 * @param description
	 * @param userid
	 * @param reqParam 只允许传入map 或者实体类
	 * @return
	 */
	public static String info(String model,String description,String userid,Object reqParam){
		StringBuffer sb = new StringBuffer("[操作模块：");
		sb.append(model);
		sb.append("    描述：");
		sb.append(description);
		sb.append("    用户信息：id=");
		sb.append(userid);
		sb.append("    参数：");
		if(reqParam!=null){
			JSONObject object = JSONObject.fromObject(reqParam);
			sb.append(object.toString());
		}
		sb.append("]");
		return sb.toString();
	}
}
