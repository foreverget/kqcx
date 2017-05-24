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

	@Override
	public BaseResponse doReleaseOrder(OrderInfoRequest orderInfoRequest) {
		BaseResponse resp = new BaseResponse();
		try {
			// 校验订单信息是否符合,如果有未完成订单，不允许发布
//			String openId = orderInfoRequest.getOpenId();
//			List<OrderInfo> orderList = orderDao.getUnEndOrderList(openId);
//			if(null!=orderList&& orderList.size()>0){
//				resp.setEnum(ResponseEnum.RELEASE_ERROR);
//				return resp;
//			}
			OrderInfo nOrderInfo = buildOrder(orderInfoRequest);
			nOrderInfo.setStatus(Constants.ORDER_STATUS_RELEASED);
			long id = orderDao.insert(nOrderInfo);
			resp.setValue(id);
			// TODO 发布后向车主推送消息
		} catch (Exception e) {
			resp.setValue("发布失败," + e.getMessage());
			LOGGER.error("doReleaseOrder error.", e);
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
	private OrderInfo buildOrder(OrderInfoRequest orderInfoRequest) {
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
		orderInfo.setVersion(0);
		orderInfo.setCreateBy("司机");
		orderInfo.setCreateTime(new Date());
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
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();  
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
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();  
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
			OrderInfo orderInfo = orderDao.selectOrderById(orderId);
			if (null != orderInfo) {
				if (orderInfo.getOpenId().equals(openId)) {
					// 获取有效乘客
					OrderPassenger orderPassenger = orderPassengerDao.selectOneOrderPassengerById(openId, orderId, "1");
					orderPassenger.setStatus("0");
					orderPassenger.setCreateBy("司机");
					orderPassenger.setUpdateTime(new Date());
					orderPassengerDao.updateOrderPassengerById(orderPassenger);
					// 将关联司机出车单剩余座位数加回
					orderInfo.setSurplusSeat(orderInfo.getSurplusSeat() + orderPassenger.getCount());
					// 状态改为发布中
					orderInfo.setStatus("0");
					orderInfo.setUpdateBy("司机");
					orderInfo.setUpdateTime(new Date());
					orderDao.updateOrderById(orderInfo);
					// TODO 移除乘客后向乘客发送推送消息
				} else {
					resp.setEnum(ResponseEnum.OPERATION_ULTRA_VIRES);
				}
			} else {
				resp.setEnum(ResponseEnum.LIST_EMPTY);
			}
		} catch (Exception e) {
			LOGGER.error("removePassenger error ", e);
			resp.setEnum(ResponseEnum.GET_TAKE_LIST_ERROR);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();  
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
			// 判断乘客是否已经约过这个车
			List<OrderPassenger> opList = orderDao.getPassengerList(orderId);
			if (null != opList && opList.size() > 0) {
				for (OrderPassenger op : opList) {
					if (op.getOpenId().equals(openId)) {
						resp.setEnum(ResponseEnum.ALREADY_HAS_THIS_ORDER);
						return resp;
					}
				}
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
			// 如果已经约满，订单状态改为客满出发
			if(order.getSurplusSeat()==0){
				order.setStatus("1");
			}
			order.setUpdateBy("乘客");
			order.setUpdateTime(new Date());
			order.setVersion(version+1);
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
			orderPassenger.setCreateBy("乘客");
			orderPassenger.setCreateTime(new Date());
			orderPassenger.setVersion(0);
			orderPassengerDao.insert(orderPassenger);
		} catch (Exception e) {
			LOGGER.error("takeOrder error ", e);
			resp.setEnum(ResponseEnum.TAKE_ERROR);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();  
		}
		return resp;
	}

}
