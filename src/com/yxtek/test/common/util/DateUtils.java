/** 
 * File Name:DateUtils.java
 * 
 * Version:1.0
 *
 * Date:2015-8-20
 *
 * Description: 日期String 工具类
 *
 * Author:erik
 * Email:erik7@126.com
 * Copyright (c) 2015 yxtek Information Co.,Ltd.
 * All Rights Reserved. 
 */ 


package com.yxtek.test.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.text.TextUtils;
import android.text.format.DateFormat;

public class DateUtils {
	
	private static final String DATE_TEMPLATE = "yyyy-MM-dd";
	
	/*
	 * 
	 * 单位：毫秒
	 */
	public static String parseDateToHHMM(long timestamp){
		String dateFormat = "kk:mm";
		String dateStr = DateFormat.format(dateFormat,timestamp).toString();
		return dateStr;
	}

	/*
	 * 
	 * 单位：毫秒
	 */
	public static String parseDateToYYYYMMDDHHMM(long timestamp){
		String dateFormat = "yyyy-MM-dd kk:mm";
		String dateStr = DateFormat.format(dateFormat,timestamp).toString();
		return dateStr;
	}

	public static String parseDateToYYYYMMDDHHMMSS(long timestamp){
		String dateFormat = "yyyy-MM-dd kk:mm:ss";
		String dateStr = DateFormat.format(dateFormat,timestamp).toString();
		return dateStr;
	}

	public static String parseDateToYYYYMMDDHHMM(String timestampStr){
		if(timestampStr == null)
			return "";
		try{
			long timestamp = Long.parseLong(timestampStr);
			return parseDateToYYYYMMDDHHMM(timestamp);
		}catch(Exception e){
			return timestampStr;
		}
	}
	
	public static String parseDateToYYYYMMDD(long timestamp) {
		String dateStr = DateFormat.format(DATE_TEMPLATE,timestamp).toString();
		return dateStr;
	}

	public static String parseDateToYYYYMMDDHHMMStr(long timestamp){
		final String dateFormat = "yyyy年MM月dd日 kk:mm";
		String dateStr = DateFormat.format(dateFormat,timestamp).toString();
		return dateStr;
	}
	
	public static void main(String arg[]){
		DateUtils.parseDateToYYYYMMDDHHMM("1363962551");
	}
	
	/**
	 * 
	 * @param timeString
	 * @return
	 */
	public static String getDateString(String timeString) {
		if(TextUtils.isEmpty(timeString))
			return "";
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setLenient(false);
		try {
			Date dt = sdf.parse(timeString);
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			return format1.format(dt);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 
	 * @param timeString
	 * @return MM-dd
	 */
	public static String getMonthDateString(String timeString) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setLenient(false);
		try {
			Date dt = sdf.parse(timeString);
			SimpleDateFormat format1 = new SimpleDateFormat("MM-dd");
			return format1.format(dt);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		return "";
	}
	
	public static boolean isSameDay(long time1,long time2) {
//		final long MS_OF_ONE_DAY = 3600000*24;
		
		Calendar cal1 = Calendar.getInstance();
	    Calendar cal2 = Calendar.getInstance();
	    cal1.setTimeInMillis(time1);
	    cal2.setTimeInMillis(time2);
		if(cal1.get(Calendar.YEAR)!=cal2.get(Calendar.YEAR))
			return false;
		
		return cal1.get(Calendar.DAY_OF_YEAR)==cal2.get(Calendar.DAY_OF_YEAR);
    }
	
	public static boolean isSameWeek(long time1,long time2) {
		Calendar cal1 = Calendar.getInstance();
	    Calendar cal2 = Calendar.getInstance();
	    cal1.setTimeInMillis(time1);
	    cal2.setTimeInMillis(time2);
	    
	    return cal1.get(Calendar.WEEK_OF_YEAR)==cal2.get(Calendar.WEEK_OF_YEAR);
	}
	
	public static boolean isSameMonth(long time1,long time2) {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTimeInMillis(time1);
		cal2.setTimeInMillis(time2);
		
		return cal1.get(Calendar.MONTH)==cal2.get(Calendar.MONTH);
	}
	
	/**
	 * 
	 * @param dateStr,格式为："yyyy-MM-dd"
	 * @return
	 */
	public static Date parseDateStr(String dateStr) {
		SimpleDateFormat format1 = new SimpleDateFormat(DATE_TEMPLATE,Locale.getDefault());
		Date date = null;
		
		try {
			date = format1.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 
	 * @param dateStr,格式为："yyyy-MM-dd"
	 * @return
	 */
	public static long parserDateStr2long(String dateStr) {
		long l = 0;
		Date date = parseDateStr(dateStr);
		if(null!=date)
			return date.getTime();
		
		return l;
	}
	
	/**
	 * 
	 * @param dateMin , 格式为"yyyy年MM月dd日 hh:mm"
	 * @return
	 */
	public static long parseDateMinStr2long(String dateMin) {
		final String dateTemp = "yyyy年MM月dd日 kk:mm";
		SimpleDateFormat format1 = new SimpleDateFormat(dateTemp,Locale.getDefault());
		
		try {
			Date date = format1.parse(dateMin);
			if(null!=date)
				return	date.getTime();
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return 0;
	}

	public static String getRunTimeStr(long sec) {
		final int SEC_ONE_MINUTE = 60;
		final int MIN_ONE_HOUR = 60;
				
		if(sec<SEC_ONE_MINUTE)
			return sec+"秒";
		
		long min = sec/SEC_ONE_MINUTE;
		if(min<MIN_ONE_HOUR)
			return min+"分"+sec%SEC_ONE_MINUTE+"秒";
		else{
			return min/MIN_ONE_HOUR+"小时"+min%MIN_ONE_HOUR+"分";
		}
		
	}
	
	/**
	 * 获取某一天时间的最后一秒的long time
	 * @param dayString eg.1970-01-01
	 * @return
	 */
	public static long getDayLastSecondTime(String dayString) {
		final String dateFormat = "yyyy-MM-dd kk:mm:ss";
		
		SimpleDateFormat format1 = new SimpleDateFormat(dateFormat,Locale.getDefault());
		
		try {
			Date date = format1.parse(dayString+" 23:59:59");
			if(null!=date)
				return	date.getTime();
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return 0;
	}
	
	/**
	 * 获取一个月前的Calendar实例
	 * @return
	 */
	public static Calendar getLastMonth() {
		Calendar calendar = Calendar.getInstance();
		int mon = calendar.get(Calendar.MONTH);
		if(mon==Calendar.JANUARY){
			int year = calendar.get(Calendar.YEAR)-1;
			calendar.set(Calendar.YEAR, year);
			calendar.set(Calendar.MONTH, Calendar.DECEMBER);
		}
		else{
			mon--;
			calendar.set(Calendar.MONTH, mon);
		}
		return calendar;
	}
}
