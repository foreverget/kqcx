package com.dc.kq.pinche.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.dc.kq.pinche.common.BaseResponse;
import com.dc.kq.pinche.common.Constants;
import com.dc.kq.pinche.common.ResponseEnum;
import com.dc.kq.pinche.dao.OrderDAO;
import com.dc.kq.pinche.dao.OrderPassengerDAO;
import com.dc.kq.pinche.dmo.OrderInfo;
import com.dc.kq.pinche.dmo.OrderPassenger;
import com.dc.kq.pinche.request.OrderInfoRequest;

/**
 * 订单service实现类
 * 
 * @author xiaogang
 *
 */
@Service("orderServiceImpl")
public class OrderServiceImpl implements OrderService {

	public static Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Autowired
	private OrderDAO orderDao;

	@Autowired
	private OrderPassengerDAO orderPassengerDao;

	@Override
	public BaseResponse myBookOrderList(String dateType) {
		return null;
	}

	@Override
	public BaseResponse myReleaseOrderList(String dateType) {
		return null;
	}

	@Override
	public BaseResponse myHistoryOrderList(String orderType) {
		return null;
	}

	@Transactional
	@Override
	public BaseResponse doReleaseOrder(OrderInfoRequest orderInfoRequest) {
		BaseResponse resp = new BaseResponse();
		// 校验订单信息是否符合
		OrderInfo nOrderInfo = checkAndBuildOrder(orderInfoRequest);
		try {
			long id = orderDao.insert(nOrderInfo);
			resp.setValue(id);
		} catch (Exception e) {
			resp.setValue("发布失败");
			LOGGER.error("save order error ", e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return resp;

	}

	/**
	 * 检查及组装最终订单实体
	 * 
	 * @param orderInfo
	 * @return
	 */
	private OrderInfo checkAndBuildOrder(OrderInfoRequest orderInfoRequest) {
		// 出车时间
		// 始
		// 终
		// 备注 可为空
		// 车牌号码 外键关联车辆信息
		// 拼车人数 先限制最大为7
		// 车费单价 先固定为5元，限制黑车行为，保证为私家车使用

		// 以下2项，如未填写，在用户基本信息表获取
		// 司机姓名
		// 联系电话

		// 检查是否重复发布
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setOpenId(orderInfoRequest.getOpenId());
		orderInfo.setName(orderInfoRequest.getName());
		orderInfo.setGoTime(orderInfoRequest.getGoTime());
		orderInfo.setStartAddr(orderInfoRequest.getStartAddr());
		orderInfo.setEndAddr(orderInfoRequest.getEndAddr());
		orderInfo.setMemo(orderInfoRequest.getMemo());
		orderInfo.setPlates(orderInfoRequest.getPlates());
		orderInfo.setReqNum(orderInfoRequest.getReqNum());
		orderInfo.setPrice(orderInfoRequest.getPrice());
		orderInfo.setMobile(orderInfoRequest.getMobile());
		return orderInfo;
	}

	@Override
	public List<OrderInfo> findReleaseOrderList(Map<String, Object> params, String dateType) {
		List<OrderInfo> orderList = orderDao.selectOrderByParams(params, dateType);
		return orderList;
	}

	@Transactional
	@Override
	public BaseResponse doBookOrder(String userId, String orderId, String count) {
		BaseResponse resp = new BaseResponse();
		// 检查该乘客是否重复预约本订单 待添加

		if (Integer.valueOf(count) <= 0) {
			resp.setValue("预约失败预约座位数需大于0");
			LOGGER.error("doBookOrder error userId=" + userId + " orderId=" + orderId + " count=" + count);
			return resp;
		}
		// 获取订单
		OrderInfo orderInfo = orderDao.selectOrderById(Long.valueOf(orderId));
		if (null == orderInfo) {
			resp.setValue("预约失败无法查到" + orderId + "订单信息");
			LOGGER.error("doBookOrder error orderId=" + orderId);
			return resp;
		}
		// 当前剩余座位数大于等于预约座位数
		if (orderInfo.getReqNum() >= Integer.valueOf(count)) {
			// 修改订单剩余乘客数
			orderInfo.setReqNum(orderInfo.getReqNum() - Integer.valueOf(count));
			// 如果订单剩余乘客数为0，订单已满
			if (orderInfo.getReqNum() == 0) {
				orderInfo.setStatus(Constants.ORDER_STATUS_FULL);
			}
			// 更新订单
			orderInfo.setUpdateTime(new Date());
			orderInfo.setUpdateBy(userId);
			orderInfo.setVersion(orderInfo.getVersion() + 1);
			orderDao.updateOrderById(orderInfo);
			// 添加乘客订单关系
			OrderPassenger orderPassenger = new OrderPassenger();
			orderPassenger.setUserId(Long.valueOf(userId));
			orderPassenger.setOrderId(Long.valueOf(orderId));
			orderPassenger.setStatus(Constants.ORDER_PASSENGER_STATUS_OK);
			orderPassenger.setCount(Integer.valueOf(count));
			orderPassenger.setCreateTime(new Date());
			orderPassenger.setCreateBy(userId);
			orderPassenger.setVersion(0);
			orderPassengerDao.insert(orderPassenger);
			resp.setValue("预约成功");
		} else {
			resp.setValue("预约失败该订单" + orderId + "已满");
			LOGGER.error("doBookOrder error orderId=" + orderId);
		}
		return resp;
	}

	@Transactional
	@Override
	public BaseResponse doCancelOrderByDriver(String orderId) {
		BaseResponse resp = new BaseResponse();
		// 检查是否可以取消，如果已经有人约车，禁止取消？ 待添加

		// 取消出车单
		// 获取订单
		OrderInfo orderInfo = orderDao.selectOrderById(Long.valueOf(orderId));
		if (null == orderInfo) {
			resp.setValue("取消失败无法查到" + orderId + "订单信息");
			LOGGER.error("doBookOrder error orderId=" + orderId);
			return resp;
		}
		orderInfo.setStatus(Constants.ORDER_STATUS_CANCLE);
		// 更新订单
		orderInfo.setUpdateTime(new Date());
		orderInfo.setUpdateBy(orderInfo.getCreateBy());
		orderInfo.setVersion(orderInfo.getVersion() + 1);
		orderDao.updateOrderById(orderInfo);
		resp.setValue("取消出车单成功");
		return resp;
	}

	@Transactional
	@Override
	public BaseResponse doCancelOrderByPassenger(String orderId, String userId) {
		BaseResponse resp = new BaseResponse();
		// 获取乘客订单关系，状态设置为取消
		OrderPassenger orderPassenger = orderPassengerDao.selectOrderPassengerById(userId, orderId);
		if (null != orderPassenger) {
			orderPassenger.setStatus(Constants.ORDER_PASSENGER_STATUS_CANCLE);
			orderPassenger.setUpdateTime(new Date());
			orderPassenger.setUpdateBy("司机");
			orderPassenger.setVersion(orderPassenger.getVersion() + 1);
			orderPassengerDao.updateOrderPassengerById(orderPassenger);
			// 更新出车订单座位数及状态
			OrderInfo orderInfo = orderDao.selectOrderById(Long.valueOf(orderId));
			// 归还座位
			orderInfo.setReqNum(orderInfo.getReqNum() + orderPassenger.getCount());
			// 状态改为已发布 未满
			orderInfo.setStatus(Constants.ORDER_STATUS_RELEASED);
			orderInfo.setUpdateTime(new Date());
			orderInfo.setUpdateBy("司机");
			orderInfo.setVersion(orderInfo.getVersion() + 1);
			orderDao.updateOrderById(orderInfo);
			resp.setValue("取消成功");
		} else {
			resp.setValue("取消失败 orderId=" + orderId + " userId=" + userId);

		}
		return resp;
	}

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
	@Override
	public BaseResponse getYcOrderList(int page, int size, String openId, int type) {
		BaseResponse resp = new BaseResponse();
		List<OrderInfo> list = new ArrayList<OrderInfo>();
		try {
			// 获取当前日期
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String time = sdf.format(new Date());
			if (type == Constants.ORDER_SEARCH_TYPE_TOM) {// 明天
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(new Date());
				calendar.add(calendar.DATE, 1);
				time = sdf.format(calendar.getTime());
			} else if (type == Constants.ORDER_SEARCH_TYPE_AFT) {// 后天
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(new Date());
				calendar.add(calendar.DATE, 2);
				time = sdf.format(calendar.getTime());
			}
			list = orderDao.getOrderList(openId, (page - 1) * size, size, type, time);
			resp.setValue(list);
		} catch (Exception e) {
			LOGGER.error("getYcOrderList error ", e);
		}
		return resp;
	}

	/**
	 * 根据orderId 获取 order信息
	 * 
	 * @param orderId
	 * @return
	 */
	@Override
	public OrderInfo getOrderDetail(long orderId) {
		OrderInfo order = new OrderInfo();
		try {
			order = orderDao.getYcOrderDetail(orderId);
		} catch (Exception e) {
			LOGGER.error("getYcOrderDetail error ", e);
		}
		return order;
	}

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
	@Override
	public BaseResponse getCcOrderList(int page, int size, String openId, int type) {
		BaseResponse resp = new BaseResponse();
		List<OrderInfo> list = new ArrayList<OrderInfo>();
		try {
			// 获取当前日期
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String time = sdf.format(new Date());
			if (type == Constants.ORDER_SEARCH_TYPE_TOM) {// 明天
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(new Date());
				calendar.add(calendar.DATE, 1);
				time = sdf.format(calendar.getTime());
			} else if (type == Constants.ORDER_SEARCH_TYPE_AFT) {// 后天
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(new Date());
				calendar.add(calendar.DATE, 2);
				time = sdf.format(calendar.getTime());
			}
			list = orderDao.getCcOrderList(openId, (page - 1) * size, size, type, time);
			resp.setValue(list);
		} catch (Exception e) {
			LOGGER.error("getCcOrderList error ", e);
		}
		return resp;
	}

	/**
	 * 获取乘客列表
	 * 
	 * @param orderId
	 * @return
	 */
	public List<OrderPassenger> getPassengerList(long orderId) {
		List<OrderPassenger> list = new ArrayList<OrderPassenger>();
		try {
			list = orderDao.getPassengerList(orderId);
		} catch (Exception e) {
			LOGGER.error("getPassengerList error ", e);
		}
		return list;
	}

	/**
	 * 取消订单
	 * 
	 * @param openId
	 * @param orderId
	 * @return
	 */
	@Override
	public BaseResponse channelOrder(String openId, long orderId) {
		BaseResponse resp = new BaseResponse();
		try {
			// 根据order查询订单详情
			OrderInfo order = orderDao.selectOrderById(orderId);
			if (null != order) {
				if (order.getOpenId().equals(openId)) {
					orderDao.channelOrder(orderId);
					// TODO 取消订单后向订单中乘客发送推送消息
				} else {
					resp.setEnum(ResponseEnum.OPERATION_ULTRA_VIRES);
				}
			} else {
				resp.setEnum(ResponseEnum.LIST_EMPTY);
			}
		} catch (Exception e) {
			LOGGER.error("channelOrder error ", e);
		}
		return resp;
	}

	/**
	 * 客满发车
	 * 
	 * @param openId
	 * @param orderId
	 * @return
	 */
	@Override
	public BaseResponse subReleaseOrder(String openId, long orderId) {
		BaseResponse resp = new BaseResponse();
		try {
			// 根据order查询订单详情
			OrderInfo order = orderDao.selectOrderById(orderId);
			if (null != order) {
				if (order.getOpenId().equals(openId)) {
					orderDao.subReleaseOrder(orderId);
				} else {
					resp.setEnum(ResponseEnum.OPERATION_ULTRA_VIRES);
				}
			} else {
				resp.setEnum(ResponseEnum.LIST_EMPTY);
			}
		} catch (Exception e) {
			LOGGER.error("subReleaseOrder error ", e);
		}
		return resp;
	}

	/**
	 * 移除乘客
	 * 
	 * @param orderId
	 * @param openId
	 * @param opId
	 *            被移除乘客openId
	 * @return
	 */
	@Override
	public BaseResponse removePassenger(long orderId, String openId, String opId) {
		BaseResponse resp = new BaseResponse();
		try {
			// 根据order查询订单详情
			OrderInfo order = orderDao.selectOrderById(orderId);
			if (null != order) {
				if (order.getOpenId().equals(openId)) {
					orderPassengerDao.updateStatusByParam(orderId, opId);
				} else {
					resp.setEnum(ResponseEnum.OPERATION_ULTRA_VIRES);
				}
			} else {
				resp.setEnum(ResponseEnum.LIST_EMPTY);
			}
		} catch (Exception e) {
			LOGGER.error("removePassenger error ", e);
		}
		return resp;
	}

}
