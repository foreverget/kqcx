package com.dc.kq.pinche.common;

public class Constants {
	/**
	 * 每页数量
	 */
	public static final int PAGE_SIZE = 10;
	
	public static final String WX_APP_ID = "wxcbec67470e5bfae3";
	
	public static final String WX_APP_SECRET = "89ac8d3274ed17390300658fffda23a4";
	
	/**
	 * 订单状态（0：发布中，1：已满，2：已送达，3：已取消）
	 */
	public static final String ORDER_STATUS_RELEASED = "0";
	public static final String ORDER_STATUS_FULL = "1";
	public static final String ORDER_STATUS_FINISH = "2";
	public static final String ORDER_STATUS_CANCLE = "3";
	
	/**
	 * 乘客订单状态  1:正常   0：取消
	 */
	public static final String ORDER_PASSENGER_STATUS_OK = "1";
	public static final String ORDER_PASSENGER_STATUS_CANCLE = "0";
	
	/**
	 * 订单查询类型 0：历史订单，1：今天，2：明天，3：后天
	 */
	public static final int ORDER_SEARCH_TYPE_HISTORY = 0;
	public static final int ORDER_SEARCH_TYPE_TODAY = 1;
	public static final int ORDER_SEARCH_TYPE_TOM = 2;
	public static final int ORDER_SEARCH_TYPE_AFT = 3;

	
}
