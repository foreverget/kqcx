package com.dc.kq.pinche.service;

import java.util.List;

import com.dc.kq.pinche.common.BaseResponse;
import com.dc.kq.pinche.dmo.OrderInfo;
import com.dc.kq.pinche.dmo.OrderPassenger;
import com.dc.kq.pinche.request.OrderInfoRequest;

/**
 * 订单service
 * 
 * @author xiaogang
 *
 */
public interface OrderService {
	/**
	 * 车主发布出车单
	 * 
	 * @param orderInfoRequest
	 *            订单信息
	 * @return
	 */
	public BaseResponse doReleaseOrder(OrderInfoRequest orderInfoRequest);

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
	 * @param opId
	 *            被移除乘客openId
	 * @return
	 */
	public BaseResponse removePassenger(long orderId, String openId, String opId);

	/**
	 * 我要约车页面列表
	 * 
	 * @param page
	 * @param size
	 * @param openId
	 * @param type
	 * @return
	 */
	public BaseResponse getTakeOrderList(int page, int size, String openId, int type);

	/**
	 * 我约
	 * 
	 * @param orderId
	 * @param openId
	 * @param count
	 * @param version
	 * 
	 * @return
	 */
	public BaseResponse takeOrder(long orderId, String openId, int count, int version);
}
