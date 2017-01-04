package com.dc.kq.pinche.service;

import com.dc.kq.pinche.common.BaseResponse;
import com.dc.kq.pinche.dmo.OrderInfo;

/**
 * 订单service
 * 
 * @author xiaogang
 *
 */
public interface OrderService {

	/**
	 * 我的约车单查询
	 * 
	 * @param dateType 1、今天 2、明天 3、后天
	 * @return
	 */
	public BaseResponse myBookOrderList(String dateType);
	
	/**
	 * 我的出车单查询
	 * 
	 * @param dateType 1、今天 2、明天 3、后天
	 * @return
	 */
	public BaseResponse myReleaseOrderList(String dateType);
	
	/**
	 * 我的历史订单查询
	 * 
	 * @param orderType 1、约车单 2、出车单
	 * @return
	 */
	public BaseResponse myHistoryOrderList(String orderType);
	
	/**
	 * 车主发布出车单
	 * 
	 * @param orderInfo 订单信息
	 * @return
	 */
	public BaseResponse doReleaseOrder(OrderInfo orderInfo);
	
	/**
	 * 车主取消出车单
	 * 
	 * @param orderId 订单ID
	 * @return
	 */
	public BaseResponse doCancelOrderByDriver(String orderId);
	
	/**
	 * 乘客约车,展示当前出车单列表
	 * 
	 * @param dateType 1、今天 2、明天 3、后天
	 * @return
	 */
	public BaseResponse findReleaseOrderList(String dateType);
	
	/**
	 * 乘客进行约车
	 * 
	 * @param orderId 订单ID
	 * @return
	 */
	public BaseResponse doBookOrder(String orderId);
	
	/**
	 * 乘客取消已约车，由车主调用，乘客无法操作该功能
	 * 
	 * @param orderId 订单ID
	 * @param userId 乘客ID
	 * @return
	 */
	public BaseResponse doCancelOrderByPassenger(String orderId,String userId);
	
}
