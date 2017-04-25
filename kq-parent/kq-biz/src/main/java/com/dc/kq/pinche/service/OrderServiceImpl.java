package com.dc.kq.pinche.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

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
import com.dc.kq.pinche.dao.UserDAO;
import com.dc.kq.pinche.dmo.OrderInfo;
import com.dc.kq.pinche.dmo.OrderPassenger;
import com.dc.kq.pinche.dmo.UserInfo;
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

	@Autowired
	private UserDAO userDAO;

	@Transactional
	@Override
	public BaseResponse doReleaseOrder(OrderInfoRequest orderInfoRequest) {
		BaseResponse resp = new BaseResponse();
		// 校验订单信息是否符合
		OrderInfo nOrderInfo = checkAndBuildOrder(orderInfoRequest);
		nOrderInfo.setStatus(Constants.ORDER_STATUS_RELEASED);
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
		orderInfo.setSurplusSeat(orderInfoRequest.getReqNum());
		return orderInfo;
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
			resp.setEnum(ResponseEnum.GET_TAKE_LIST_ERROR);
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
			resp.setEnum(ResponseEnum.GET_TAKE_LIST_ERROR);
		}
		return resp;
	}

	/**
	 * 我要约车页面列表
	 * 
	 * @param page
	 * @param size
	 * @param openId
	 * @param type
	 * @return
	 */
	@Override
	public BaseResponse getTakeOrderList(int page, int size, String openId, int type) {
		BaseResponse resp = new BaseResponse();
		// TODO 判断微信用户是否存在
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
			list = orderDao.getTakeOrderList((page - 1) * size, size, type, time);
			resp.setValue(list);
		} catch (Exception e) {
			LOGGER.error("getUserTakeList error ", e);
			resp.setEnum(ResponseEnum.GET_TAKE_LIST_ERROR);
		}
		return resp;
	}

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
	@Override
	public BaseResponse takeOrder(long orderId, String openId, int count, int version) {
		BaseResponse resp = new BaseResponse();
		try {
			// 根据orderId查询订单信息
			OrderInfo order = orderDao.selectOrderById(orderId);
			if (null == order) {
				resp.setEnum(ResponseEnum.NO_ORDER);
				return resp;
			}
			// 比较version 如果version不相等 则订单信息已经发生了变化，则返回
			if (order.getVersion() != version) {
				resp.setEnum(ResponseEnum.ORDER_VERSION_ERROR);
				return resp;
			}
			// 比较剩余座位数如果小于此次乘车人数则返回错误提示
			if (order.getSurplusSeat() < count) {
				resp.setEnum(ResponseEnum.NO_SEAT);
				return resp;
			}
			// version 相等 更新订单表，更新version和剩余座位，剩余座位等于之前的之前剩余座位-此次乘车人数
			order.setSurplusSeat(order.getSurplusSeat() - count);
			orderDao.updateOrderById(order);
			// 根据openId查询乘车人信息
			UserInfo userInfo = userDAO.selectUserByOpenId(openId);
			// 新增乘车人与订单关系表
			OrderPassenger orderPassenger = new OrderPassenger();
			orderPassenger.setOrderId(orderId);
			orderPassenger.setOpenId(openId);
			orderPassenger.setStatus(Constants.ORDER_PASSENGER_STATUS_OK);
			orderPassenger.setCount(count);
			orderPassenger.setName(userInfo.getName());
			orderPassenger.setMobile(userInfo.getMobile());
			orderPassenger.setVersion(0);
			orderPassengerDao.insert(orderPassenger);
		} catch (Exception e) {
			LOGGER.error("takeOrder error ", e);
			resp.setEnum(ResponseEnum.TAKE_ERROR);
			// TODO 回滚数据
		}
		return resp;
	}

}
