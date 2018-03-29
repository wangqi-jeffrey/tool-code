package com.tyiti.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Pattern;

public class DateTest {

	public static void main(String[] args) throws ParseException {
		// SimpleDateFormat df = new SimpleDateFormat("yyyy-M-dd");
		String str = "2014-3-1 1:22:6";
		//System.out.println(formatDateStr(str));
		SimpleDateFormat sdf = new SimpleDateFormat("hhmm");
		System.out.println(sdf.format(new Date()));
		/*
		 * SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 * long to = df.parse("2008-4-26 23:32:30").getTime(); long from =
		 * df.parse("2008-4-25 23:32:30").getTime(); long dst =
		 * df.parse("2008-4-25 23:32:30").getTime(); //System.out.println((to -
		 * from) / (1000 * 60 * 60 * 24));
		 * System.err.println(dst>=from&&dst<=to);
		 * 
		 * Date date = new Date(); Calendar now = Calendar.getInstance();
		 * now.setTime(date); System.out.println("年: " +
		 * now.get(Calendar.YEAR)); System.out.println("月: " +
		 * (now.get(Calendar.MONTH) + 1) + ""); System.out.println("日: " +
		 * now.get(Calendar.DAY_OF_MONTH)); System.out.println("时: " +
		 * now.get(Calendar.HOUR_OF_DAY)); System.out.println("分: " +
		 * now.get(Calendar.MINUTE)); System.out.println("秒: " +
		 * now.get(Calendar.SECOND)); System.out.println("当前时间毫秒数：" +
		 * now.getTimeInMillis()); System.out.println(now.getTime());
		 * 
		 * SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMdd HH:mm:ss"); Date
		 * d = sdf.parse("20160712"); System.out.println("---"+d);
		 */
	}
	
	public static final String formatDateStr(String disorderStr){
		// 将全部日期拼装成 yyyy-MM-dd HH:mm:ss 格式
		/**
		 * 2016/01/01 12:30:20   
		   2016-01-15 11:30:10 -->最大位数
		   2016-1-15 11:30:10
		   2016-1-1  -->最小位数 8
		 */
		// 先判断位数
		if (disorderStr.length() > 7 && disorderStr.length() < 20) {
			disorderStr = disorderStr.replaceAll("/", "-");
			String[] blankArr = disorderStr.split(" ");
			String[] arr = blankArr[0].split("-");
			if (arr[1].length() == 1) {
				arr[1] = "0" + arr[1];
			}
			if (arr[2].length() == 1) {
				arr[2] = "0" + arr[2];
			}
			String timeStr = "";
			if (blankArr.length == 2) {
				String[] timeArr = blankArr[1].split(":");
				if (timeArr[0].length() == 1) {
					timeArr[0] = "0" + timeArr[0];
				}
				if (timeArr[1].length() == 1) {
					timeArr[1] = "0" + timeArr[1];
				}
				if (timeArr[2].length() == 1) {
					timeArr[2] = "0" + timeArr[2];
				}
				timeStr = " " + timeArr[0] + ":" + timeArr[1] + ":" + timeArr[2];
			}else{
				timeStr = " 00:00:00";
			}
			return arr[0] + "-" + arr[1] + "-" + arr[2] + timeStr;
		}else{
			return null;
		}
	}

	public static String name(String dateStr) {
		HashMap<String, String> dateRegFormat = new HashMap<String, String>();
		dateRegFormat.put("^\\d{4}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D*$","yyyy-MM-dd-HH-mm-ss");// 2014年3月12日 13时5分34秒，2014-03-12 // 12:05:34，2014/3/12 12:5:34
		dateRegFormat.put("^\\d{4}\\D+\\d{2}\\D+\\d{2}\\D+\\d{2}\\D+\\d{2}$","yyyy-MM-dd-HH-mm");// 2014-03-12 12:05
		dateRegFormat.put("^\\d{4}\\D+\\d{2}\\D+\\d{2}\\D+\\d{2}$","yyyy-MM-dd-HH");// 2014-03-12 12
		dateRegFormat.put("^\\d{4}\\D+\\d{2}\\D+\\d{2}$", "yyyy-MM-dd");// 2014-03-12
		dateRegFormat.put("^\\d{4}\\D+\\d{2}$", "yyyy-MM");// 2014-03
		dateRegFormat.put("^\\d{4}$", "yyyy");// 2014
		dateRegFormat.put("^\\d{14}$", "yyyyMMddHHmmss");// 20140312120534
		dateRegFormat.put("^\\d{12}$", "yyyyMMddHHmm");// 201403121205
		dateRegFormat.put("^\\d{10}$", "yyyyMMddHH");// 2014031212
		dateRegFormat.put("^\\d{8}$", "yyyyMMdd");// 20140312
		dateRegFormat.put("^\\d{6}$", "yyyyMM");// 201403
		dateRegFormat.put("^\\d{2}\\s*:\\s*\\d{2}\\s*:\\s*\\d{2}$","yyyy-MM-dd-HH-mm-ss");// 13:05:34 拼接当前日期
		dateRegFormat.put("^\\d{2}\\s*:\\s*\\d{2}$", "yyyy-MM-dd-HH-mm");// 13:05
																			// 拼接当前日期
		dateRegFormat.put("^\\d{2}\\D+\\d{1,2}\\D+\\d{1,2}$", "yy-MM-dd");// 14.10.18(年.月.日)
		dateRegFormat.put("^\\d{1,2}\\D+\\d{1,2}$", "yyyy-dd-MM");// 30.12(日.月)
																	// 拼接当前年份
		dateRegFormat.put("^\\d{1,2}\\D+\\d{1,2}\\D+\\d{4}$", "dd-MM-yyyy");// 12.21.2013(日.月.年)

		String curDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DateFormat formatter2;
		String dateReplace;
		String strSuccess = "";
		try {
			for (String key : dateRegFormat.keySet()) {
				if (Pattern.compile(key).matcher(dateStr).matches()) {
					formatter2 = new SimpleDateFormat(dateRegFormat.get(key));
					if (key.equals("^\\d{2}\\s*:\\s*\\d{2}\\s*:\\s*\\d{2}$")
							|| key.equals("^\\d{2}\\s*:\\s*\\d{2}$")) {// 13:05:34
																		// 或
																		// 13:05
																		// 拼接当前日期
						dateStr = curDate + "-" + dateStr;
					} else if (key.equals("^\\d{1,2}\\D+\\d{1,2}$")) {// 21.1
																		// (日.月)
																		// 拼接当前年份
						dateStr = curDate.substring(0, 4) + "-" + dateStr;
					}

					dateReplace = dateStr.replaceAll("\\D+", "-");

					strSuccess = formatter1.format(formatter2.parse(dateReplace));
					break;
				}
			}
			System.out.println("========="+strSuccess);
			return strSuccess;
		} catch (Exception e) {
			System.err.println("-----------------日期格式无效:" + dateStr);
			throw new Exception("日期格式无效");
		} finally {
			return strSuccess;
		}
	}

}
