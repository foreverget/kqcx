package com.dc.kq.pinche.service;

import java.util.List;
import java.util.Map;

import com.dc.kq.pinche.common.BaseResponse;
import com.dc.kq.pinche.dmo.OrderInfo;
import com.dc.kq.pinche.dmo.OrderPassenger;
import com.dc.kq.pinche.request.OrderInfoRequest;
import com.fasterxml.jackson.databind.deser.Deserializers.Base;

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
	 * @param dateType
	 *            1、今天 2、明天 3、后天
	 * @return
	 */
	public BaseResponse myBookOrderList(String dateType);

	/**
	 * 我的出车单查询
	 * 
	 * @param dateType
	 *            1、今天 2、明天 3、后天
	 * @return
	 */
	public BaseResponse myReleaseOrderList(String dateType);

	/**
	 * 我的历史订单查询
	 * 
	 * @param orderType
	 *            1、约车单 2、出车单
	 * @return
	 */
	public BaseResponse myHistoryOrderList(String orderType);

	/**
	 * 车主发布出车单
	 * 
	 * @param orderInfoRequest
	 *            订单信息
	 * @return
	 */
	public BaseResponse doReleaseOrder(OrderInfoRequest orderInfoRequest);

	/**
	 * 车主取消出车单
	 * 
	 * @param orderId
	 *            订单ID
	 * @return
	 */
	public BaseResponse doCancelOrderByDriver(String orderId);

	/**
	 * 乘客约车,展示当前出车单列表
	 * 
	 * @param dateType
	 *            1、今天 2、明天 3、后天
	 * @return
	 */
	public List<OrderInfo> findReleaseOrderList(Map<String, Object> params, String dateType);

	/**
	 * 乘客进行约车
	 * 
	 * @param userId
	 *            用户ID
	 * @param orderId
	 *            订单ID
	 * @param count
	 *            预定座位数
	 * @return
	 */
	public BaseResponse doBookOrder(String userId, String orderId, String count);

	/**
	 * 乘客取消已约车，由车主调用，乘客无法操作该功能
	 * 
	 * @param orderId
	 *            订单ID
	 * @param userId
	 *            乘客ID
	 * @return
	 */
	public BaseResponse doCancelOrderByPassenger(String orderId, String userId);

	/**
	 * 历史订单--我的约车单
	 * 
	 * @param page
	 * @param size
	 * @param openId
	 * @param type
	 *            0：历史订单，1：今天，2：明天，3：后天
	 * @return
	 */
	public BaseResponse getYcOrderList(int page, int size, String openId, int type);

	/**
	 * 根据orderId 获取 order信息
	 * 
	 * @param orderId
	 * @return
	 */
	public OrderInfo getOrderDetail(long orderId);

	/**
	 * 我的出车单
	 * 
	 * @param page
	 * @param size
	 * @param openId
	 * @param type
	 *            0：历史订单，1：今天，2：明天，3：后天
	 * @return
	 */
	public BaseResponse getCcOrderList(int page, int size, String openId, int type);

	/**
	 * 获取乘客列表
	 * 
	 * @return
	 */
	public List<OrderPassenger> getPassengerList(long orderId);

	/**
	 * 取消订单
	 * 
	 * @param openId
	 * @param orderId
	 * @return
	 */
	public BaseResponse channelOrder(String openId, long orderId);

	/**
	 * 客满发车
	 * 
	 * @param openId
	 * @param orderId
	 * @return
	 */
	public BaseResponse subReleaseOrder(String openId, long orderId);

	/**
	 * 移除乘客
	 * 
	 * @param orderId
	 * @param openId
	 * @param opId 被移除乘客openId
	 * @return
	 */
	public BaseResponse removePassenger(long orderId, String openId, String opId);
}
