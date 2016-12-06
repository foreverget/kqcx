package com.dc.kq.pinche.service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class CrmConstant {

	/**
	 * 正常班打卡开始时间
	 */
	public static Time NORMAL_START = Time.valueOf("08:50");

	/**
	 * 正常班打卡结束时间
	 */
	public static Time NORMAL_END = Time.valueOf("17:50");

	/**
	 * 加班打卡开始时间
	 */
	public static Time OVERTIME_START = Time.valueOf("8:50");

	/**
	 * 加班打卡结束时间
	 */
	public static Time OVERTIME_END = Time.valueOf("17:50");

	/**
	 * 白加班打卡开始时间
	 */
	public static Time NEWOVERTIME_START = Time.valueOf("8:50");

	/**
	 * 白加班打卡结束时间
	 */
	public static Time NEWOVERTIME_END = Time.valueOf("21:00");

	/**
	 * 正常班打卡开始时间
	 */
	public static Time SPECIAL_NORMAL_START = Time.valueOf("9:30");

	/**
	 * 正常班打卡结束时间
	 */
	public static Time SPECIAL_NORMAL_END = Time.valueOf("18:30");

	/**
	 * 加班打卡开始时间
	 */
	public static Time SPECIAL_OVERTIME_START = Time.valueOf("09:30");

	/**
	 * 加班打卡结束时间
	 */
	public static Time SPECIAL_OVERTIME_END = Time.valueOf("18:30");

	/**
	 * 白加班打卡开始时间
	 */
	public static Time SPECIAL_NEWOVERTIME_START = Time.valueOf("18:30");

	/**
	 * 白加班打卡结束时间
	 */
	public static Time SPECIAL_NEWOVERTIME_END = Time.valueOf("21:30");

	/**
	 * 特殊的组别
	 */
	@SuppressWarnings("serial")
	public static List<String> SPECIALGROUP = new ArrayList<String>() {

		{
			add("帕拉蒂尼销售三部二组");
			add("帕拉蒂尼销售三部四组");
		}
	};

	/**
	 * 特殊的部门
	 */
	@SuppressWarnings("serial")
	public static List<String> SPECIALDEP = new ArrayList<String>() {

		{
			add("第六军团销售二部");
		}
	};
	/**
	 * 迟到
	 */
	public static String CHECK_RESULT_LATE_STR = "迟到";
	/**
	 * 早退
	 */
	public static String CHECK_RESULT_EARLY_STR = "早退";
	/**
	 * 迟到且早退
	 */
	public static String CHECK_RESULT_LATE_AND_EARLY_STR = "迟到且早退";
	/**
	 * 正常班
	 */
	public static String NORMAL_STR = "正常班";
	/**
	 * 加班
	 */
	public static String OVERTIME_STR = "加班";
	/**
	 * 未打卡
	 */
	public static String CHECK_RESULT_NO_SIGN="未打卡";
	/**
	 * 旷工
	 */
	public static String CHECK_RESULT_NO_ABSE="旷工";
	
	
	
}
