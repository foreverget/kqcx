package com.dc.kq.pinche.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;

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
import com.dc.kq.pinche.util.WxMsgUtil;
import com.dc.kq.pinche.wx.Template;
import com.dc.kq.pinche.wx.TemplateParam;

/**
 * 订单service实现类
 * 
 * @author xiaogang
 *
 */
@Service("orderServiceImpl")
public class OrderServiceImpl implements OrderService {

	public static Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);
	// 锁对象
	private Lock lock = new ReentrantLock();

	@Autowired
	private OrderDAO orderDao;

	@Autowired
	private OrderPassengerDAO orderPassengerDao;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Override
	public BaseResponse doReleaseOrder(OrderInfoRequest orderInfoRequest) {
		BaseResponse resp = new BaseResponse();
		try {
			// 校验订单信息是否符合,如果有当天有未完成订单，不允许发布
			String openId = orderInfoRequest.getOpenId();
			String goTime = orderInfoRequest.getGoTime();
			String time = goTime.substring(0, 10);
			List<OrderInfo> orderList = orderDao.getUnEndOrderList(openId, time);
			if (null != orderList && orderList.size() > 0) {
				resp.setEnum(ResponseEnum.RELEASE_ERROR);
				return resp;
			}
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
					// 取消订单后向订单中乘客发送推送消息
					sendToUserWhenChannelOrder(order);
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
					OrderPassenger orderPassenger = orderPassengerDao.selectOneOrderPassengerById(opId, orderId, "1");
					if (null != orderPassenger) {
						orderPassenger.setStatus("0");
						orderPassenger.setCreateBy(orderInfo.getName());
						orderPassenger.setUpdateTime(new Date());
						orderPassengerDao.updateOrderPassengerById(orderPassenger);
					}
					// 将关联司机出车单剩余座位数加回
					orderInfo.setSurplusSeat(orderInfo.getSurplusSeat() + orderPassenger.getCount());
					// 状态改为发布中
					orderInfo.setStatus("0");
					orderInfo.setUpdateBy(orderInfo.getName());
					orderInfo.setUpdateTime(new Date());
					orderDao.updateOrderById(orderInfo);
					// 移除乘客后向乘客发送推送消息
					sendToUserWhenRemoveUser(orderInfo, orderPassenger);
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
		// 获取锁
		lock.lock();
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
			// if (order.getVersion() != version) {
			// resp.setEnum(ResponseEnum.ORDER_VERSION_ERROR);
			// return resp;
			// }
			// 比较剩余座位数如果小于此次乘车人数则返回错误提示
			if (order.getSurplusSeat() < count) {
				resp.setEnum(ResponseEnum.NO_SEAT);
				return resp;
			}
			// version 相等 更新订单表，更新version和剩余座位，剩余座位等于之前的之前剩余座位-此次乘车人数
			order.setSurplusSeat(order.getSurplusSeat() - count);
			// 如果已经约满，订单状态改为客满出发
			if (order.getSurplusSeat() == 0) {
				order.setStatus("1");
			}
			order.setUpdateBy("乘客");
			order.setUpdateTime(new Date());
			order.setVersion(version + 1);
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

			// 乘客可约车后向司机发送推送
			sendToDriverWhenJoin(order, orderPassenger);
			// 乘客可约车后向乘客自己发送推送
			sendToUserWhenJoin(order, orderPassenger);
		} catch (Exception e) {
			LOGGER.error("takeOrder error ", e);
			resp.setEnum(ResponseEnum.TAKE_ERROR);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		} finally {
			// 释放锁
			lock.unlock();
		}
		return resp;
	}

	/**
	 * 乘客可约车后向司机发送推送
	 * 
	 * @param order
	 * @param orderPassenger
	 */
	private void sendToDriverWhenJoin(OrderInfo order, OrderPassenger orderPassenger) {
		// 构建消息模板
		Template template = new Template();
		template.setTemplateId("Nh-3MmriH7v1YpUXtkkGwQJ8vwGibw4lYtlhYP4se3w");
		template.setToUser(order.getOpenId());
		// 构建消息模板参数
		List<TemplateParam> templateParamList = new ArrayList<TemplateParam>();
		TemplateParam tp = new TemplateParam("first", "尊敬的 车主先生/女士：  有新的拼友预定了您的座位。", "");
		TemplateParam tp1 = new TemplateParam("keyword1", orderPassenger.getName() + " 先生/女士", "");
		TemplateParam tp2 = new TemplateParam("keyword2", orderPassenger.getCount() + "人", "");
		TemplateParam tp3 = new TemplateParam("keyword3", orderPassenger.getMobile(), "");
		TemplateParam tp4 = new TemplateParam("keyword4", order.getStartAddr(), "");
		TemplateParam tp5 = new TemplateParam("remark", "主动电话联系他/她。 相聚是缘，多点理解，多点爱心，生活就很美好。", "");
		templateParamList.add(tp);
		templateParamList.add(tp1);
		templateParamList.add(tp2);
		templateParamList.add(tp3);
		templateParamList.add(tp4);
		templateParamList.add(tp5);
		template.setTemplateParamList(templateParamList);
		// 获取token
		String token = redisTemplate.opsForValue().get("access_token");
		// 发送模板信息
		WxMsgUtil.sendTemplateMsg(token, template);
	}

	/**
	 * 乘客可约车后向乘客自己发送推送
	 * 
	 * @param order
	 * @param orderPassenger
	 */
	private void sendToUserWhenJoin(OrderInfo order, OrderPassenger orderPassenger) {
		// 构建消息模板
		Template template = new Template();
		template.setTemplateId("OMEFGT1UZQR9PUUNJX1slNBDjwMfo0VJZP4s4H9YtrQ");
		template.setToUser(order.getOpenId());
		// 构建消息模板参数
		List<TemplateParam> templateParamList = new ArrayList<TemplateParam>();
		TemplateParam tp = new TemplateParam("first", "拼车成功", "");
		TemplateParam tp1 = new TemplateParam("keyword1", order.getStartAddr(), "");
		TemplateParam tp2 = new TemplateParam("keyword2", order.getEndAddr(), "");
		TemplateParam tp3 = new TemplateParam("keyword3", order.getGoTime(), "");
		TemplateParam tp4 = new TemplateParam("remark", "您已经拼车成功，请点击【详情】查看。", "");
		templateParamList.add(tp);
		templateParamList.add(tp1);
		templateParamList.add(tp2);
		templateParamList.add(tp3);
		templateParamList.add(tp4);
		template.setTemplateParamList(templateParamList);
		// 获取token
		String token = redisTemplate.opsForValue().get("access_token");
		// 发送模板信息
		WxMsgUtil.sendTemplateMsg(token, template);
	}

	/**
	 * 司机移除乘客后向乘客发送推送消息
	 * 
	 * @param order
	 * @param orderPassenger
	 */
	private void sendToUserWhenRemoveUser(OrderInfo order, OrderPassenger orderPassenger) {
		// 通过订单Id查询乘客信息
		List<OrderPassenger> list = orderDao.getPassengerList(order.getId());
		if (!CollectionUtils.isEmpty(list)) {
			for (OrderPassenger op : list) {
				// 构建消息模板
				Template template = new Template();
				template.setTemplateId("oykLDUfxWzEHgMNJsBUHwtatIwfK1m6lf0TyGorTMWo");
				template.setToUser(op.getOpenId());
				// 构建消息模板参数
				List<TemplateParam> templateParamList = new ArrayList<TemplateParam>();
				TemplateParam tp = new TemplateParam("first", "乘客您好，您已经被司机移除拼车订单！", "");
				TemplateParam tp1 = new TemplateParam("keyword1", order.getGoTime(), "");
				TemplateParam tp2 = new TemplateParam("keyword2", "司机：" + order.getName(), "");
				TemplateParam tp3 = new TemplateParam("remark", "请您及时预约其他车辆", "");
				templateParamList.add(tp);
				templateParamList.add(tp1);
				templateParamList.add(tp2);
				templateParamList.add(tp3);
				template.setTemplateParamList(templateParamList);
				// 获取token
				String token = redisTemplate.opsForValue().get("access_token");
				// 发送模板信息
				WxMsgUtil.sendTemplateMsg(token, template);
			}
		}
	}

	/**
	 * 取消订单后向订单中乘客发送推送消息
	 */
	private void sendToUserWhenChannelOrder(OrderInfo order) {
		// 通过订单Id查询乘客信息
		List<OrderPassenger> list = orderDao.getPassengerList(order.getId());
		if (!CollectionUtils.isEmpty(list)) {
			for (OrderPassenger op : list) {
				// 构建消息模板
				Template template = new Template();
				template.setTemplateId("oykLDUfxWzEHgMNJsBUHwtatIwfK1m6lf0TyGorTMWo");
				template.setToUser(op.getOpenId());
				// 构建消息模板参数
				List<TemplateParam> templateParamList = new ArrayList<TemplateParam>();
				TemplateParam tp = new TemplateParam("first", "乘客您好，拼车订单已经被司机取消！", "");
				TemplateParam tp1 = new TemplateParam("keyword1", order.getGoTime(), "");
				TemplateParam tp2 = new TemplateParam("keyword2", order.getName(), "");
				TemplateParam tp3 = new TemplateParam("remark", "请您及时预约其他车辆", "");
				templateParamList.add(tp);
				templateParamList.add(tp1);
				templateParamList.add(tp2);
				templateParamList.add(tp3);
				template.setTemplateParamList(templateParamList);
				// 获取token
				String token = redisTemplate.opsForValue().get("access_token");
				// 发送模板信息
				WxMsgUtil.sendTemplateMsg(token, template);
			}
		}
	}
}
