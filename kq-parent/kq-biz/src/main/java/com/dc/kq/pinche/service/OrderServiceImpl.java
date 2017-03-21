package com.dc.kq.pinche.service;

import java.util.Date;
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
	private OrderInfo checkAndBuildOrder(OrderInfoRequest orderInfoRequest){
		// 出车时间
		// 始
		// 终
		// 备注 可为空
		// 车牌号码  外键关联车辆信息
		// 拼车人数  先限制最大为7
		// 车费单价  先固定为5元，限制黑车行为，保证为私家车使用
		
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
		// 检查该乘客是否重复预约本订单  待添加
		
		if (Integer.valueOf(count) <= 0) {
			resp.setValue("预约失败预约座位数需大于0");
			LOGGER.error("doBookOrder error userId=" + userId + " orderId=" + orderId + " count=" + count);
			return resp;
		}
		// 获取订单
		OrderInfo orderInfo = orderDao.selectOrderById(Long.valueOf(orderId));
		if(null==orderInfo){
			resp.setValue("预约失败无法查到"+orderId+"订单信息");
			LOGGER.error("doBookOrder error orderId=" + orderId);
			return resp;
		}
		// 当前剩余座位数大于等于预约座位数
		if (orderInfo.getReqNum() >= Integer.valueOf(count)) {
			// 修改订单剩余乘客数
			orderInfo.setReqNum(orderInfo.getReqNum() - Integer.valueOf(count));
			// 如果订单剩余乘客数为0，订单已满
			if(orderInfo.getReqNum()==0){
				orderInfo.setStatus(Constants.ORDER_STATUS_FULL);
			}
			// 更新订单
			orderInfo.setUpdateTime(new Date());
			orderInfo.setUpdateBy(userId);
			orderInfo.setVersion(orderInfo.getVersion()+1);
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
			resp.setValue("预约失败该订单"+orderId+"已满");
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
		if(null==orderInfo){
			resp.setValue("取消失败无法查到"+orderId+"订单信息");
			LOGGER.error("doBookOrder error orderId=" + orderId);
			return resp;
		}
		orderInfo.setStatus(Constants.ORDER_STATUS_CANCLE);
		// 更新订单
		orderInfo.setUpdateTime(new Date());
		orderInfo.setUpdateBy(orderInfo.getCreateBy());
		orderInfo.setVersion(orderInfo.getVersion()+1);
		orderDao.updateOrderById(orderInfo);
		resp.setValue("取消出车单成功");
		return resp;
	}

	@Transactional
	@Override
	public BaseResponse doCancelOrderByPassenger(String orderId, String userId) {
		BaseResponse resp = new BaseResponse();
		// 获取乘客订单关系，状态设置为取消
		OrderPassenger orderPassenger = orderPassengerDao.selectOrderPassengerById(userId,orderId);
		if(null != orderPassenger){
			orderPassenger.setStatus(Constants.ORDER_PASSENGER_STATUS_CANCLE);
			orderPassenger.setUpdateTime(new Date());
			orderPassenger.setUpdateBy("司机");
			orderPassenger.setVersion(orderPassenger.getVersion()+1);
			orderPassengerDao.updateOrderPassengerById(orderPassenger);
			// 更新出车订单座位数及状态
			OrderInfo orderInfo = orderDao.selectOrderById(Long.valueOf(orderId));
			// 归还座位
			orderInfo.setReqNum(orderInfo.getReqNum() + orderPassenger.getCount());
			// 状态改为已发布 未满 
			orderInfo.setStatus(Constants.ORDER_STATUS_RELEASED);
			orderInfo.setUpdateTime(new Date());
			orderInfo.setUpdateBy("司机");
			orderInfo.setVersion(orderInfo.getVersion()+1);
			orderDao.updateOrderById(orderInfo);
			resp.setValue("取消成功");
		}else{
			resp.setValue("取消失败 orderId="+orderId+" userId="+userId);
			
		}
		return resp;
	}
	
}
