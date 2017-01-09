package com.dc.kq.pinche.service;

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
	public BaseResponse doReleaseOrder(OrderInfo orderInfo) {
		
		BaseResponse resp = new BaseResponse();
		// 校验订单信息是否符合
		OrderInfo nOrderInfo = checkAndBuildOrder(orderInfo);
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
	private OrderInfo checkAndBuildOrder(OrderInfo orderInfo){
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
		
		return orderInfo;
	}

	@Override
	public BaseResponse findReleaseOrderList(String dateType) {
		return null;
	}

	@Override
	public BaseResponse doBookOrder(String userId, String orderId, String count) {
		BaseResponse resp = new BaseResponse();
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
		if (orderInfo.getPassengerNum() >= Integer.valueOf(count)) {
			// 修改订单剩余乘客数
			orderInfo.setPassengerNum(orderInfo.getPassengerNum() - Integer.valueOf(count));
			// 如果订单剩余乘客数为0，订单已满
			if(orderInfo.getPassengerNum()==0){
				orderInfo.setStatus(Constants.ORDER_STATUS_FULL);
			}
			// 更新订单
			
			// 添加乘客订单关系
			OrderPassenger orderPassenger = new OrderPassenger();
			orderPassenger.setUserId(Long.valueOf(userId));
			orderPassenger.setOrderId(Long.valueOf(orderId));
			orderPassenger.setStatus(Constants.ORDER_PASSENGER_STATUS_OK);
			orderPassenger.setCount(Integer.valueOf(count));
			orderPassengerDao.insert(orderPassenger);
			resp.setValue("预约成功");
		}
		return resp;
	}

	@Override
	public BaseResponse doCancelOrderByDriver(String orderId) {
		return null;
	}

	@Override
	public BaseResponse doCancelOrderByPassenger(String orderId, String userId) {
		return null;
	}
	
}
